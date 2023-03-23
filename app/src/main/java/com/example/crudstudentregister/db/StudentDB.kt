package com.example.crudstudentregister.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDB : RoomDatabase() {

    abstract fun studentDao(): StudentDAO
    companion object {
        @Volatile
        private var INSTANCE : StudentDB? = null

        fun getInstance(ctx: Context): StudentDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(ctx.applicationContext, StudentDB::class.java, "student_data_database").build()
                }
                return instance
            }
        }
    }

}