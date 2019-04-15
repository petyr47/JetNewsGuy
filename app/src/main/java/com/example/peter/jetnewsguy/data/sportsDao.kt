package com.example.peter.jetnewsguy.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface sportsDao {

    @Insert
    suspend fun insert(news: sportsNews)

    @Update
    suspend fun update(news: sportsNews)

    @Delete
    suspend fun delete(news: sportsNews)

    @Query("DELETE FROM sports_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM sports_table")
    suspend fun getAllItems(): LiveData<List<sportsNews>>

    @Insert
    suspend fun insertAll(news: List<sportsNews>)

    @Transaction
    suspend fun updateData(data: List<sportsNews>?) {
        deleteAll()
        insertAll(data!!)
    }
}