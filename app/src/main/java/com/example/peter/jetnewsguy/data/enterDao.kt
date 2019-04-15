package com.example.peter.jetnewsguy.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface enterDao {
    @Insert
    suspend fun insert(news: enterNews)

    @Update
    suspend fun update(news: enterNews)

    @Delete
    suspend fun delete(news: enterNews)

    @Query("DELETE FROM enter_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM enter_table")
    suspend fun getAllItems(): LiveData<List<enterNews>>

    @Insert
    suspend fun insertAll(news: List<enterNews>)

    @Transaction
    suspend fun updateData(data: List<enterNews>?) {
        deleteAll()
        insertAll(data!!)
    }



}