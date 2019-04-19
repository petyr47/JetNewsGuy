package com.example.peter.jetnewsguy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Article::class], version = 3, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {

    abstract fun newsDao():NewsDao


    companion object {
        @Volatile private var instance: AppDatabase? =null

        fun getInstance(context: Context): AppDatabase{
            return instance ?: synchronized(this){
                instance ?: buildDatabase(context).also { instance=it }
            }
        }
        private fun buildDatabase (context: Context):AppDatabase{
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "App_Database"
            )
                .addCallback(object :RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        //PopulateDbAsyncTask(instance).execute()
                    }
                }

                )
                .fallbackToDestructiveMigration()
                .build()
        }
    }





}