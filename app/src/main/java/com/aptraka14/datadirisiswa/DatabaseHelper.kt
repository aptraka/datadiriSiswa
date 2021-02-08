package com.aptraka14.datadirisiswa

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import com.aptraka14.datadirisiswa.model.dataModel

class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DBNAME, null, DBVERSION) {
    companion object {
        private val DBVERSION = 1
        private val DBNAME = "DatadiriSiswa"
        private val DBTABLE = "TableDataSiswa"
        private val KEYNAMA = "nama"
        private val KEYNOHP = "no_hp"
        private val KEYGENDER = "gender"
        private val KEYJENJANG = "jenjang"
        private val KEYHOBI = "hobi"
        private val KEYALAMAT = "alamat"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = ("CREATE TABLE " + DBTABLE + " ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEYNAMA + " TEXT," +
                KEYNOHP + " TEXT," +
                KEYGENDER + " TEXT," +
                KEYJENJANG + " TEXT," +
                KEYHOBI + " TEXT," +
                KEYALAMAT + " TEXT)")
        db!!.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + DBTABLE)
        onCreate(db)
    }

    fun addData(model: dataModel): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEYNAMA, model.nama)
        contentValues.put(KEYNOHP, model.hp)
        contentValues.put(KEYGENDER, model.gender)
        contentValues.put(KEYJENJANG, model.jenjang)
        contentValues.put(KEYHOBI, model.hobi)
        contentValues.put(KEYALAMAT, model.alamat)

        val sukses = db.insert(DBTABLE, null, contentValues)
        db.close()
        return sukses
    }

    fun viewData(): List<dataModel> {
        val dataList: ArrayList<dataModel> = ArrayList<dataModel>()
        val selectQuery = "SELECT * FROM $DBTABLE"
        val db = this.readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var dataId: Int
        var dataName: String
        var dataJenjang: String
        var dataNohp: String
        var dataGender: String
        var dataHobi: String
        var dataAlamat: String
        if (cursor.moveToFirst()) {
            do {
                dataId = cursor.getInt(cursor.getColumnIndex("id"))
                dataName = cursor.getString(cursor.getColumnIndex("nama"))
                dataJenjang = cursor.getString(cursor.getColumnIndex("jenjang"))
                dataNohp = cursor.getString(cursor.getColumnIndex("no_hp"))
                dataGender = cursor.getString(cursor.getColumnIndex("gender"))
                dataHobi = cursor.getString(cursor.getColumnIndex("hobi"))
                dataAlamat = cursor.getString(cursor.getColumnIndex("alamat"))
                val mData = dataModel(
                    id = dataId,
                    nama = dataName,
                    hp = dataNohp,
                    gender = dataGender,
                    jenjang = dataJenjang,
                    hobi = dataHobi,
                    alamat = dataAlamat
                )
                dataList.add(mData)
            } while (cursor.moveToNext())
        }
        return dataList
    }

}
