package com.example.mobileappproject.DBManager

import android.app.DownloadManager.Query
import android.util.Log
import java.sql.ResultSet
import java.sql.Statement

data class DB_dc_std_info(
    var id: String = "", //접속 id         STD_ID
    var pw: String = "", //접속 pw         STD_PW
    var name: String = "", //학생 이름       STD_NAME
    var dep: String = "", //학생 학부        STD_DEP
    var sid: String = "" //학생 학번         STD_SID---
)

data class DB_dc_std_course_info(
    var sid: String = "", //학생 학번        STD_SID---
    var name: String = "", //학생 이름       STD_NAME
    var code: String = "", //수강 과목 코드   SUB_NAME
    var sub: String = "" //수강 과목 이름     SUB_CODE---
)

data class DB_dc_sub_info(
    var name: String = "", //과목 이름       SUB_NAME
    var code: String = "", //과목 코드       SUB_CODE---
    var pro: String = "", //교수 이름        SUB_PRO
    var std: String = "" //청강생 이름        SUB_STD---
)

data class DB_dc_sub_hw(
    var seq: Int = 0,       //순번            SUB_HW_SEQ---
    var code: String = "",  //과목 코드         SUB_CODE---
    var sub: String = "",   //과목 이름         SUB_NAME
    var date: String = "",  //과제 일자         SUB_HW_DATE
    var hw_name: String = ""//과제 명          SUB_HW_NAME
)

data class DB_dc_sub_test(
    var seq: Int = 0,       //순번            SUB_TEST_SEQ---
    var code: String = "",  //과목 코드         SUB_CODE---
    var sub: String = "",   //과목 이름         SUB_NAME
    var date: String = "",  //시험 일자         SUB_TEST_DATE
    var test_name: String = ""//시험 명          SUB_TEST_NAME
)

data class DB_dc_sub_noti(
    var seq: Int = 0,       //순번            SUB_NOTI_SEQ---
    var code: String = "",  //과목 코드         SUB_CODE---
    var sub: String = "",   //과목 이름         SUB_NAME
    var title: String = "", //과목 공지사항 제목    SUB_NOTI_TITLE
    var written: String = "",                   //SUB_NOTI_WRITTEN
    var cont: String = "",                      //SUB_NOTI_CONT
    var date: String = "",  //일자         SUB_NOTI_DATEE
)

object DBInfoManager {
    private val DEBUGMODE = false

    var dbDcStdInfoMap: MutableMap<String, DB_dc_std_info> = mutableMapOf("" to DB_dc_std_info())
    var dbDcStdCourseInfoMap: MutableMap<Pair<String, String>, DB_dc_std_course_info> = mutableMapOf(Pair("", "") to DB_dc_std_course_info())
    var dbDcSubInfoMap: MutableMap<Pair<String, String>, DB_dc_sub_info> = mutableMapOf(Pair("","") to DB_dc_sub_info())
    var dbDcSubHwMap: MutableMap<Pair<String, String>, DB_dc_sub_hw> = mutableMapOf(Pair("","") to DB_dc_sub_hw())
    var dbDcSubTestMap: MutableMap<Pair<String, String>, DB_dc_sub_test> = mutableMapOf(Pair("","") to DB_dc_sub_test())
    var dbDcSubNotiMap: MutableMap<Pair<String, String>, DB_dc_sub_noti> = mutableMapOf(Pair("","") to DB_dc_sub_noti())

    fun initMap(){
        //dbDcStdInfoMap

        try {

            get_STDINFO_Table()
            get_STDCourseINFO_Table()
            get_SUBINFO_Table()
            get_SUBHW_Table()
            get_SUBTEST_Table()
            get_SUBNOTI_Table()

        }catch (e: Exception){
            e.printStackTrace()
        }

    }

    private fun get_STDINFO_Table(){
        try{
            val statement: Statement
            statement = DBConnectManager.conn.createStatement()
            val resultSet: ResultSet
            resultSet = statement.executeQuery(
                "select * from myproject.STD_INFO_TB sit "
            )

            if(DEBUGMODE) println(
                "STD_ID" + "   /  " + "STD_PW" + "  /   " + "STD_NAME" + "  /   " + "STD_DEP" + "  /   " + "STD_SID"
            )
            while (resultSet.next()) {
                var temp: DB_dc_std_info = DB_dc_std_info()

                temp.id = resultSet.getString("STD_ID")
                temp.pw = resultSet.getString("STD_PW")
                temp.name = resultSet.getString("STD_NAME")
                temp.dep = resultSet.getString("STD_DEP")
                temp.sid = resultSet.getString("STD_SID").trim()
                if(DEBUGMODE) println(
                    temp.id + "   /  " + temp.pw + "  /   " + temp.name + "  /   " + temp.dep + "  /   " + temp.sid
                )

                dbDcStdInfoMap.put(temp.sid, temp)
            }
            if(DEBUGMODE) println(
                "dbDcStdInfoMap count is " + dbDcStdInfoMap.count()
            )

            if(DEBUGMODE) println("check dbDcStdInfoMap------------")
            if(DEBUGMODE) for(it in dbDcStdInfoMap){
                println(
                    "key : " + it.key + "\t\t" + "value : " + it.value
                )
            }

            resultSet.close();
            statement.close();
        } catch (e: Exception){
        //
        }
    }

    private fun get_STDCourseINFO_Table(){
        try{
            val statement: Statement
            statement = DBConnectManager.conn.createStatement()
            val resultSet: ResultSet
            resultSet = statement.executeQuery(
                "select * from myproject.STD_COU_TB sit "
            )

            if(DEBUGMODE) println(
                "STD_SID" + "   /  " + "STD_NAME" + "  /   " + "SUB_CODE" + "  /   " + "SUB_NAME"
            )
            while (resultSet.next()) {
                var temp: DB_dc_std_course_info = DB_dc_std_course_info()

                temp.sid = resultSet.getString("STD_SID")
                temp.name = resultSet.getString("STD_NAME")
                temp.sub = resultSet.getString("SUB_NAME")
                temp.code = resultSet.getString("SUB_CODE").trim()
                if(DEBUGMODE) println(
                    temp.sid + "   /  " + temp.name + "  /   " + temp.sub + "  /   " + temp.code
                )

                val pair: Pair<String, String> = Pair(temp.sid, temp.code)
                dbDcStdCourseInfoMap.put(pair, temp)
            }

            if(DEBUGMODE) println(
                "dbDcStdCourseInfoMap count is " + dbDcStdCourseInfoMap.count()
            )

            if(DEBUGMODE) println("check dbDcStdCourseInfoMap------------")
            if(DEBUGMODE) for(it in dbDcStdCourseInfoMap){
                println(
                    "key : " + it.key + "\t\t" + "value : " + it.value
                )
            }

            resultSet.close();
            statement.close();
        } catch (e: Exception){
            //
        }
    }

    private fun get_SUBINFO_Table(){
        try{
            val statement: Statement
            statement = DBConnectManager.conn.createStatement()
            val resultSet: ResultSet
            resultSet = statement.executeQuery(
                "select * from myproject.SUB_INFO_TB sit "
            )

            if(DEBUGMODE) Log.d(
                "DB test","sub_name" + "   /  " + "sub_code" + "  /   " + "sub_pro" + "  /   " + "sub_std"
            )
            while (resultSet.next()) {
                var temp: DB_dc_sub_info = DB_dc_sub_info()

                temp.name = resultSet.getString("SUB_NAME")
                temp.code = resultSet.getString("SUB_CODE")
                temp.pro = resultSet.getString("SUB_PRO")
                temp.std = resultSet.getString("SUB_STD").trim()
                if(DEBUGMODE) Log.d(
                    "DB test",temp.name + "   /  " + temp.code + "  /   " + temp.pro + "  /   " + temp.std
                )

                val pair = Pair(temp.code, temp.std)
                dbDcSubInfoMap.put(pair, temp)
            }

            if(DEBUGMODE) println(
                "dbDcSubInfoMap count is " + dbDcSubInfoMap.count()
            )
            if(DEBUGMODE) println("check dbDcSubInfoMap------------")
            if(DEBUGMODE) for(it in dbDcSubInfoMap){
                println(
                    "key : " + it.key + "\t\t" + "value : " + it.value
                )
            }

            resultSet.close();
            statement.close();
        } catch (e: Exception){
            //
        }
    }

    private fun get_SUBHW_Table(){
        try{
            val statement: Statement
            statement = DBConnectManager.conn.createStatement()
            val resultSet: ResultSet
            resultSet = statement.executeQuery(
                "select * from myproject.SUB_HW_TB sit "
            )

            while (resultSet.next()) {
                var temp: DB_dc_sub_hw = DB_dc_sub_hw()

                temp.seq = resultSet.getInt("SUB_HW_SEQ")
                temp.code = resultSet.getString("SUB_CODE")
                temp.sub = resultSet.getString("SUB_NAME")
                temp.hw_name = resultSet.getString("SUB_HW_NAME")
                temp.date = resultSet.getString("SUB_HW_DATE").trim()
                if(DEBUGMODE) Log.d(
                    "DB test",temp.sub + "   /  " + temp.code + "  /   " + temp.hw_name + "  /   " + temp.date
                )

                val pair = Pair(temp.code, temp.sub)
                dbDcSubHwMap.put(pair, temp)
            }
            resultSet.close();
            statement.close();
        } catch (e: Exception){
            //
        }
    }

    private fun get_SUBTEST_Table(){
        try{
            val statement: Statement
            statement = DBConnectManager.conn.createStatement()
            val resultSet: ResultSet
            resultSet = statement.executeQuery(
                "select * from myproject.SUB_TEST_TB sit "
            )

            while (resultSet.next()) {
                var temp: DB_dc_sub_test = DB_dc_sub_test()

                temp.seq = resultSet.getInt("SUB_TEST_SEQ")
                temp.code = resultSet.getString("SUB_CODE")
                temp.sub = resultSet.getString("SUB_NAME")
                temp.test_name = resultSet.getString("SUB_TEST_NAME")
                temp.date = resultSet.getString("SUB_TEST_DATE").trim()
                if(DEBUGMODE) Log.d(
                    "DB test",temp.sub + "   /  " + temp.code + "  /   " + temp.test_name + "  /   " + temp.date
                )

                val pair = Pair(temp.code, temp.sub)
                dbDcSubTestMap.put(pair, temp)
            }
            resultSet.close();
            statement.close();
        } catch (e: Exception){
            //
        }
    }

    private fun get_SUBNOTI_Table(){
        try{
            val statement: Statement
            statement = DBConnectManager.conn.createStatement()
            val resultSet: ResultSet
            resultSet = statement.executeQuery(
                "select * from myproject.SUB_NOTI_TB sit "
            )

            while (resultSet.next()) {
                var temp: DB_dc_sub_noti = DB_dc_sub_noti()

                temp.seq = resultSet.getInt("SUB_NOTI_SEQ")
                temp.code = resultSet.getString("SUB_CODE")
                temp.sub = resultSet.getString("SUB_NAME")
                temp.title = resultSet.getString("SUB_NOTI_TITLE")
                temp.written = resultSet.getString("SUB_NOTI_WRITTEN")
                temp.cont = resultSet.getString("SUB_NOTI_CONT")
                temp.date = resultSet.getString("SUB_NOTI_DATE").trim()
                if(true) Log.d(
                    "DB test",temp.code
                            + "  /   " + temp.sub
                            + "  /   " + temp.title
                            + "  /   " + temp.written
                            + "  /   " + temp.cont
                            + "  /   " + temp.date

                )

                val pair = Pair(temp.code, temp.sub)
                dbDcSubNotiMap.put(pair, temp)
            }
            resultSet.close();
            statement.close();
        } catch (e: Exception){
            //
        }
    }
}