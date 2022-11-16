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
    var sub: String = "" //수강 과목         STD_SUB---
)

data class DB_dc_sub_info(
    var name: String = "", //과목 이름       SUB_NAME
    var code: String = "", //과목 코드       SUB_CODE---
    var pro: String = "", //교수 이름        SUB_PRO
    var std: String = "" //청강생 이름        SUB_STD---
)

object DBInfoManager {
    private val DEBUGMODE = false

    var dbDcStdInfoMap: MutableMap<String, DB_dc_std_info> = mutableMapOf("" to DB_dc_std_info())
    var dbDcStdCourseInfoMap: MutableMap<Pair<String, String>, DB_dc_std_course_info> = mutableMapOf(Pair("", "") to DB_dc_std_course_info())
    var dbDcSubInfoMap: MutableMap<Pair<String, String>, DB_dc_sub_info> = mutableMapOf(Pair("","") to DB_dc_sub_info())

    fun initMap(){
        //dbDcStdInfoMap

        get_STDINFO_Table()
        get_STDCourseINFO_Table()
        get_SUBINFO_Table()

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
                "STD_SID" + "   /  " + "STD_NAME" + "  /   " + "STD_SUB"
            )
            while (resultSet.next()) {
                var temp: DB_dc_std_course_info = DB_dc_std_course_info()

                temp.sid = resultSet.getString("STD_SID")
                temp.name = resultSet.getString("STD_NAME")
                temp.sub = resultSet.getString("STD_SUB").trim()
                if(DEBUGMODE) println(
                    temp.sid + "   /  " + temp.name + "  /   " + temp.sub
                )

                val pair: Pair<String, String> = Pair(temp.sid, temp.sub)
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

}