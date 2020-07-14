package com.example.carbonmanage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity

    class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
//クラス内のprivate定数を宣言するためにcompanion objectブロックとする。
companion object {
    private const val DATABASE_NAME = "CarbonManage.db"

    private const val DATABASE_VERSION = 1
}

    override fun onCreate(db: SQLiteDatabase?) {
        val sb = StringBuilder()
        sb.append( "CREATE TABLE carbonManage (" )
        sb.append( "_id INTEGER PRIMARY KEY AUTOINCREMENT," )
        sb.append( "date TEXT," )
        sb.append( "time TEXT," )
        sb.append( "mealcase TEXT," )
        sb.append( "meal TEXT," )
        sb.append( "amount INTEGER," )
        sb.append( "unit TEXT," )
        sb.append( "carbon REAL" )
        sb.append( ");" )
        val sql = sb.toString()

        db?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

}