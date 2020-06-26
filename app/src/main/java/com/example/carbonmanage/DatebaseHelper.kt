package com.example.carbonmanage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity

class DatebaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
//クラス内のprivate定数を宣言するためにcompanion objectブロックとする。
companion object {
    private const val DATABASE_NAME = "CarbonManage.db"

    private const val DATABASE_VERSION = 1
}

    override fun onCreate(p0: SQLiteDatabase?) {
        val sb = StringBuilder()

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}