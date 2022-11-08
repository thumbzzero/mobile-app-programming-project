package com.example.mobileappproject.data

import android.content.Context

class GetNoticeThread(val context:Context) :Thread() {

    override fun run() {
        val dbHelper = DBHelper(context,"localDB.db",null,1)
        val db = dbHelper.writableDatabase
        val sql ="insert into SearchDataTable (title, time, site, views, site_address) values (?, ?, ?, ?, ?)"


    }
}