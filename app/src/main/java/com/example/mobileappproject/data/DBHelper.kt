package com.example.mobileappproject.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
): SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("test","데이터베이스가 생성되었습니다")
        val sql = ("create table NoticeInfo("
                + "title text primary key, "
                + "date text not null, "
                + "site text not null, "
                + "views text not null, "
                + "site_address text not null"
                + ")")

        db?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        when(p1){
            1->{

            }
            2->{

            }
            3->{

            }
        }
        Log.d("test","old:$p1, new:$p2")
    }
}