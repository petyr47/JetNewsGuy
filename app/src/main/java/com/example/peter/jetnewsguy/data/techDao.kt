package com.example.peter.jetnewsguy.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface techDao {
    @Insert
    suspend fun insert(news: techNews)

    @Update
    suspend fun update(news: techNews)

    @Delete
    suspend fun delete(news: techNews)

    @Query("DELETE FROM tech_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM tech_table")
    suspend fun getAllItems(): LiveData<List<techNews>>

    @Insert
    suspend fun insertAll(news: List<techNews>)

    @Transaction
    suspend fun updateData(data: List<techNews>?) {
        deleteAll()
        insertAll(data!!)
    }
}