package com.example.mobileappproject.data

import android.content.Context
import android.util.Log
import org.jsoup.Jsoup

class GetNoticeThread(val context:Context) :Thread() {
    val dbHelper = DBHelper(context,"localDB.db",null,1)
    val db = dbHelper.writableDatabase
    val sql ="insert into SearchDataTable (title, time, site, views, site_address) values (?, ?, ?, ?, ?)"

    override fun run() {
        try {
            val doc =Jsoup.connect("https://computer.knu.ac.kr/bbs/board.php?bo_table=sub5_1&sca=%EC%9D%BC%EB%B0%98%EA%B3%B5%EC%A7%80")
                .get()
            val element = doc.select("tbody tr")
            for (elem in element){
                val title = elem.select("div.bo_tit").text()
                val pageUrl = elem.select("a").attr("abs:href")
                val time = elem.select("td.td_datetime.hidden-xs").text()
                val views = elem.select("td.td_num.hidden-xs").text()
                val site = "컴학"
                val arg = arrayOf<String>(title,time,site,views,pageUrl)

                db.execSQL(sql,arg)

                Log.d("test","제목:$title")
            }


            db.close()

        }catch (e:Exception){ }



    }
}