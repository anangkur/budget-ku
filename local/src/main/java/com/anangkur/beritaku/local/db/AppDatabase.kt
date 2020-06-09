package com.anangkur.beritaku.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anangkur.beritaku.local.dao.AppDao
import com.anangkur.beritaku.local.Const
import com.anangkur.beritaku.local.model.ArticleCached

@Database(entities = [ArticleCached::class], version = 2)
abstract class AppDatabase: RoomDatabase(){

    abstract fun getDao(): AppDao

    companion object{
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null){
                synchronized(this){
                    if (INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, Const.DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                    return INSTANCE!!
                }
            }
            return INSTANCE!!
        }
    }
}