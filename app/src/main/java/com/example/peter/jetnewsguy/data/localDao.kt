package com.example.peter.jetnewsguy.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface localDao {

    @Insert
    suspend fun insert(news: localNews)

    @Update
    suspend fun update(news: localNews)

    @Delete
    suspend fun delete(news: localNews)

    @Query("DELETE FROM local_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM local_table")
    suspend fun getAllItems(): LiveData<List<localNews>>

    @Insert
    suspend fun insertAll(news: List<localNews>)

    @Transaction
    suspend fun updateData(data: List<localNews>?) {
        deleteAll()
        insertAll(data!!)
    }
}