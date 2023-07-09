package com.example.statsapp.starter

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "statsapp.db"
        private const val DATABASE_VERSION = 1

        // Tabel pengguna
        private const val TABLE_USERS = "users"
        private const val COLUMN_USER_ID = "id"
        private const val COLUMN_USER_EMAIL = "email"
        private const val COLUMN_USER_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Membuat tabel pengguna
        val createUsersTable = ("CREATE TABLE $TABLE_USERS ("
                + "$COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_USER_EMAIL TEXT,"
                + "$COLUMN_USER_PASSWORD TEXT)")
        db.execSQL(createUsersTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Menghapus tabel pengguna jika sudah ada
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun addUser(email: String, password: String): Long {
        val db = this.writableDatabase

        // Membuat nilai yang akan dimasukkan ke dalam tabel pengguna
        val values = ContentValues()
        values.put(COLUMN_USER_EMAIL, email)
        values.put(COLUMN_USER_PASSWORD, password)

        // Menyisipkan data ke dalam tabel pengguna
        val id = db.insert(TABLE_USERS, null, values)
        db.close()
        return id
    }

    @SuppressLint("Range")
    fun getUser(email: String): User? {
        val db = this.readableDatabase

        // Mengambil pengguna berdasarkan email
        val selectQuery = "SELECT * FROM $TABLE_USERS WHERE $COLUMN_USER_EMAIL = ?"
        val cursor = db.rawQuery(selectQuery, arrayOf(email))

        val user: User?

        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID))
            val password = cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD))

            // Membuat objek User dari hasil query
            user = User(id, email, password)
        } else {
            user = null
        }

        cursor.close()
        db.close()
        return user
    }
}
