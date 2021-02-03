package com.rendyanto.uasapp

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import java.sql.SQLException
import java.sql.SQLTimeoutException

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME,
    null, DATABASE_VERSION) {
    companion object{
        val DATABASE_NAME = "myaps.db"
        val DATABASE_VERSION = 1
        private val SQL_CREATE_USER = "CREATE TABLE " +
                DataInfo.UserTable.TABLE_NAME + "("+
                DataInfo.UserTable.COL_NAMA+" VARCHAR(200) PRIMARY KEY, " +
                DataInfo.UserTable.COL_PASS + " TEXT, " +
                DataInfo.UserTable.COL_EMAIL + " TEXT, " +
                DataInfo.UserTable.COL_ALAMAT + " VARCHAR(200), " +
                DataInfo.UserTable.COL_NOHP + " TEXT)"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " +
                DataInfo.UserTable.TABLE_NAME
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_USER)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion:
    Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    @Throws(SQLiteConstraintException::class)
    fun RegisterUser(namain: String, passin:String, emailin: String,
                     alamatin: String, nohpin: String) {
        val db = writableDatabase
        val namatable = DataInfo.UserTable.TABLE_NAME
        val namat = DataInfo.UserTable.COL_NAMA
        val passt = DataInfo.UserTable.COL_PASS
        val emailt = DataInfo.UserTable.COL_EMAIL
        val alamatt = DataInfo.UserTable.COL_ALAMAT
        val nohpt = DataInfo.UserTable.COL_NOHP
        var sql = "INSERT INTO " + namatable + " (" + namat + ", " + passt+
                ", " + emailt + ", " + alamatt + ", " + nohpt +
                ") VALUES('" + namain + "', '" + passin + "', '" + emailin + "', '" +
                alamatin + "', '" + nohpin + "')"
        db.execSQL(sql)
    }
    fun cekUser(namain: String): String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""
        try {
            cursor = db.rawQuery("SELECT COUNT("+ DataInfo.UserTable.COL_NAMA
                    +") as jumlah FROM "+ DataInfo.UserTable.TABLE_NAME + " WHERE " +
                    DataInfo.UserTable.COL_NAMA + "=='" + namain +"'" , null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }
        if (cursor!!.moveToFirst()){
            jumlah =
                cursor.getString(cursor.getColumnIndex(DataInfo.UserTable.COL_JUMLAH))
        }
        return jumlah
    }
    fun cekLogin(namain: String, passin: String): String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""
        try {
            cursor = db.rawQuery("SELECT COUNT("+ DataInfo.UserTable.COL_NAMA
                    +") as jumlah FROM "+ DataInfo.UserTable.TABLE_NAME + " WHERE " +
                    DataInfo.UserTable.COL_NAMA + "=='" + namain +"' AND " +
                    DataInfo.UserTable.COL_PASS + "=='" + passin + "'" , null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }
        if (cursor!!.moveToFirst()){
            jumlah =
                cursor.getString(cursor.getColumnIndex(DataInfo.UserTable.COL_JUMLAH))
        }
        return jumlah
    }

}