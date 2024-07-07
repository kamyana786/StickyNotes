package com.farhan.stickynotesinkotlin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [NoteItem::class],
    version = 1,
    exportSchema = false  // Make sure exportSchema is set properly
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteItemDao(): NoteItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database_v9"
                )
                    .fallbackToDestructiveMigration()
                    .build()  // Remove allowMainThreadQueries() for production apps
                INSTANCE = instance
                instance
            }
        }
    }
}

