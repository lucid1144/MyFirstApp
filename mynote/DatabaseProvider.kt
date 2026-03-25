package com.example.mynote

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(lock = this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                klass = AppDatabase::class.java,
                name = "task-database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}