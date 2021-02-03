package com.rendyanto.uasapp

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelperTanaman(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        val DATABASE_NAME = "Users.db"
        val DATABASE_VERSION = 1
        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBInfoTanaman.UserInput.TABLE_NAME + " (" + DBInfoTanaman.UserInput.COL_ID +" VARCHAR(200) PRIMARY KEY, " + DBInfoTanaman.UserInput.COL_UKURAN + " TEXT, " + DBInfoTanaman.UserInput.COL_JENIS + " VARCHAR(200), " + DBInfoTanaman.UserInput.COL_HARGA +" TEXT, "+ DBInfoTanaman.UserInput.COL_STOK +" VARCHAR(200))"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBInfoTanaman.UserInput.TABLE_NAME
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertData(idin: String, ukurin: String, jenisin: String, hargain: String, stokin: String): Boolean {
        val db = writableDatabase
        val namatablet = DBInfoTanaman.UserInput.TABLE_NAME
        val idt = DBInfoTanaman.UserInput.COL_ID
        val ukurant = DBInfoTanaman.UserInput.COL_UKURAN
        val jenist = DBInfoTanaman.UserInput.COL_JENIS
        val hargat = DBInfoTanaman.UserInput.COL_HARGA
        val stokt = DBInfoTanaman.UserInput.COL_STOK
        var sql = "INSERT INTO "+ namatablet +"("+idt+", "+ukurant+", "+jenist+", "+hargat+", "+stokt+") " +
                "VALUES('"+idin+"', '"+ukurin+"', '"+jenisin+"', '"+hargain+"', '"+stokin+"')"
        db.execSQL(sql)
        return true
    }

    fun fullData():ArrayList<DbModelTanaman> {
//        val users = ArrayList<DBModel>()
        val users = arrayListOf<DbModelTanaman>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM "+DBInfoTanaman.UserInput.TABLE_NAME, null)
        } catch (e: SQLException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var idt: String
        var ukt: String
        var jenist: String
        var hrgt: String
        var stokt: String
        if (cursor!!.moveToFirst()){
            while (cursor.isAfterLast==false){
                idt = cursor.getString(cursor.getColumnIndex(DBInfoTanaman.UserInput.COL_ID))
                ukt = cursor.getString(cursor.getColumnIndex(DBInfoTanaman.UserInput.COL_UKURAN))
                jenist = cursor.getString(cursor.getColumnIndex(DBInfoTanaman.UserInput.COL_JENIS))
                hrgt = cursor.getString(cursor.getColumnIndex(DBInfoTanaman.UserInput.COL_HARGA))
                stokt = cursor.getString(cursor.getColumnIndex(DBInfoTanaman.UserInput.COL_STOK))

                users.add(DbModelTanaman(idt, ukt, jenist, hrgt, stokt))
                cursor.moveToNext()
            }
        }
        return  users
    }
    fun deleteTanaman(idin: String){
        val db = writableDatabase
        val namatablet = DBInfoTanaman.UserInput.TABLE_NAME
        val idt = DBInfoTanaman.UserInput.COL_ID
        val sql = "delete from "+namatablet+" where "+idt+"='"+idin+"'"
        db.execSQL(sql)
    }
    fun updateTanaman(idin: String, ukurin: String, jenisin: String, hargain: String, stokin: String): Boolean {
        val db = writableDatabase
        val namatablet = DBInfoTanaman.UserInput.TABLE_NAME
        val idt = DBInfoTanaman.UserInput.COL_ID
        val ukurant = DBInfoTanaman.UserInput.COL_UKURAN
        val jenist = DBInfoTanaman.UserInput.COL_JENIS
        val hargat = DBInfoTanaman.UserInput.COL_HARGA
        val stokt = DBInfoTanaman.UserInput.COL_STOK
        var sql = "UPDATE "+ namatablet + " SET "+ ukurant+"='"+ukurin+"', "+jenist+"='"+jenisin+"', "+hargat+"='"+hargain+"', "+stokt+"='"+stokin+"' "+" WHERE "+idt+"='"+idin+"'"
        db.execSQL(sql)
        return true
    }

}
