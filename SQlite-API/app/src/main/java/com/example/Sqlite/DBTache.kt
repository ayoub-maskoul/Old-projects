package com.example.Sqlite

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


import android.content.ContentValues

import android.database.Cursor


class DBTache(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "dbtaches.db"
        private const val DATABASE_VERSION = 1


        private const val TABLE_TACHE = "tache"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITRE = "titre"
        private const val COLUMN_DATE = "date"
        private const val COLUMN_STATUT = "statut"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_TACHE (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_TITRE TEXT," +
                "$COLUMN_DATE TEXT," +
                "$COLUMN_STATUT TEXT" +
                ")"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_TACHE"
        db?.execSQL(dropTableQuery)
        onCreate(db)

    }



    @SuppressLint("Range")
    fun getAllTache(db: SQLiteDatabase): ArrayList<Tache> {
        val taches = ArrayList<Tache>()
        val selectQuery = "SELECT * FROM $TABLE_TACHE"
        val cursor: Cursor? = db.rawQuery(selectQuery, null)
        cursor?.let {
            if (it.moveToFirst()) {
                do {
                    val id = it.getInt(it.getColumnIndex(COLUMN_ID))
                    val titre = it.getString(it.getColumnIndex(COLUMN_TITRE))
                    val date = it.getString(it.getColumnIndex(COLUMN_DATE))
                    val statut = it.getString(it.getColumnIndex(COLUMN_STATUT))
                    val tache = Tache(id, titre, date, statut)
                    taches.add(tache)
                } while (it.moveToNext())
            }
            it.close()
        }
        return taches
    }

    fun deleteAllTaches(db: SQLiteDatabase) {
        db.delete(TABLE_TACHE, null, null)
    }

    fun addTache(db: SQLiteDatabase, tache: Tache): Long {
        val values = ContentValues()
        values.put(COLUMN_TITRE, tache.titre)
        values.put(COLUMN_DATE, tache.date)
        values.put(COLUMN_STATUT, tache.status)
        return db.insert(TABLE_TACHE, null, values)
        }
    fun deleteTache(db: SQLiteDatabase, id: Int) {

        db.delete(TABLE_TACHE, "id=?", arrayOf(id.toString()))
        }

    fun updateTache(db: SQLiteDatabase, id: Int, tache: Tache) {
        val values = ContentValues()
        values.put(COLUMN_TITRE, tache.titre)
        values.put(COLUMN_DATE, tache.date)
        values.put(COLUMN_STATUT, tache.status)

        db.update(TABLE_TACHE, values,"id=?", arrayOf(id.toString()))
        }



}