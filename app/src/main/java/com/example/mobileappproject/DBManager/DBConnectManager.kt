package com.example.mobileappproject.DBManager

import android.util.Log
import java.sql.*

object DBConnectManager{
    private val DEBUGMODE = false

    lateinit var conn: Connection
    lateinit var url: String
    lateinit var uid: String
    lateinit var upw: String
    val tagERR = "DB test"


    @JvmStatic
    fun connection(){
        if(DEBUGMODE) Log.e(tagERR, "DB connection start")

        initSetting()

        if(DEBUGMODE) Log.e(tagERR, "DB Connecting start!")
        //jdbc8이상을 사용할 경우 "com.mysql.cj.jdbc.Driver"을 사용해야함.
        //문제는 안드로이드에서는 jdbc8이상을 아직 지원하지 않음.
        // 만일 사용하려 할 경우 "java.sql.SQLType" Class를 찾지 못하여 Exception이 발생할 것임
        //따라서 필요에 의해 jdbc 8.0.31 대신에 jdbc 5.1.49를 사용하게 됨.
        try {
            Class.forName("com.mysql.jdbc.Driver")
            conn = DriverManager.getConnection(url, uid, upw)

            DBInfoManager.initMap()

        } catch (e: SQLException){
            //SQLNonTransientConnectionException
            e.printStackTrace()
        } catch (e: Exception){
            //
        }

        if (DEBUGMODE) Log.e(tagERR, "DB Connecting end!")
    }

    @JvmStatic
    fun disconnection(){
        if (DEBUGMODE) Log.e(tagERR, "DB disconnection start")
        try {
            conn?.close()
        } catch (e: Exception){
            Log.e(tagERR, "DB close Exception!")
        }
        if (DEBUGMODE) Log.e(tagERR, "DB disconnection end")
    }

    private fun initSetting(){

        if (DEBUGMODE) Log.e(tagERR, "DB Init Setting start!")

        url = "jdbc:mysql://database-1.cufw1avtqftd.ap-northeast-2.rds.amazonaws.com:3306/myproject?"
                                                                /* localhost 대신 127.0.0.1 사용 가능
                                                                * 3308(기본 3306) : 설치 시 지정한 포트 번호
                                                                * 맨 뒤 : 연결 할 DB 이름 */

        url += "useSSL=false&" // 인증방식 설정 : SSL로 접속할 것인가?
        url += "serverTimezone=UTC&" // 서버시간 설정
                                    // or "serverTimezone=UTC&"
                                    // or "serverTimezone=Asia/Seoul&"
        url += "useUnicode=true&" // 유니코드 사용여부 설정
        url += "characterEncoding=UTF-8&" // 인코딩 방식 지정
        url += "allowPublicKeyRetrieval=true"   // mysql 연동 시 에러 처리
                                                // 8.0 이후 설정 추가해야 오류 발생하지 X

                /* url : MySQL DB의 접속 경로
			  * uid : MySQL DB의 접속 계정 (사용자아이디)
			  * upw : MySQL DB의 접속 비밀번호 (인증코드) */

        //uid = "eotjd0508"
        //upw = "Random34734!"

        uid = "root"
        upw = "1234"

        if (DEBUGMODE) Log.e(tagERR, "DB Init Setting end!")
    }
}