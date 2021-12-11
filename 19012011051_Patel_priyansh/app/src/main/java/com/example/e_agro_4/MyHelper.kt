package com.example.e_agro_4

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyHelper(context: Context):SQLiteOpenHelper(context,"USERSDATABASE",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE USERINFO(_id integer primary key autoincrement,NAME TEXT,PASSWORD TEXT)")
        db?.execSQL("INSERT INTO USERINFO(NAME,PASSWORD)VALUES('hello','hello@123')")
        db?.execSQL("INSERT INTO USERINFO(NAME,PASSWORD)VALUES('world','world@123')")
        db?.execSQL("INSERT INTO USERINFO(NAME,PASSWORD)VALUES('ok','ok@123')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}