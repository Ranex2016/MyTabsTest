package com.example.mytabstest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mytabstest.pojo.Album
import com.example.mytabstest.pojo.AlbumDetails

@Database(entities = [AlbumDetails::class, Album::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {

            synchronized(LOCK) {
                //Если БД существует..
                db?.let { return it }

                //иначе создадим новую...
                val instance = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
                db = instance
                return instance
            }
        }
    }

    abstract fun albumDetailsDao(): AlbumDao
}