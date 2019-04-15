package com.example.peter.jetnewsguy.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface topDao {
    @Insert
    suspend fun insert(news: topNews)

    @Update
    suspend fun update(news: topNews)

    @Delete
    suspend fun delete(news: topNews)

    @Query("DELETE FROM top_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM top_table")
    suspend fun getAllItems(): LiveData<List<topNews>>

    @Insert
    suspend fun insertAll(news: List<topNews>)

    @Transaction
    suspend fun updateData(data: List<topNews>?) {
        deleteAll()
        insertAll(data!!)
    }
}