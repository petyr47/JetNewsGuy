package com.example.peter.jetnewsguy.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NewsDao {
    @Insert
    suspend fun insert(news: Article)

    @Update
    suspend fun update(news: Article)

    @Delete
    suspend fun delete(news: Article)

    @Query("DELETE FROM news_table")
    suspend fun deleteAll()


    @Query("SELECT * FROM news_table WHERE category = :category")
    suspend fun getNews(category: String): List<Article>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: List<Article>)

//   @Transaction
//    suspend fun updateData(data: List<Article>) {
//        deleteAll()
//        insertAll(data)
//    }
}