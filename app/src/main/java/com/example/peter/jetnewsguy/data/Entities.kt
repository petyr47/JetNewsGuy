package com.example.peter.jetnewsguy.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "top_table")
data class topNews(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description")val desc: String,
    @SerializedName("url") val url: String,
    @SerializedName("name") val sourceName: String,
    @SerializedName("urlToImage")val urlImage: String,
    @SerializedName("publishedAt")val pubAt: String
)

@Entity(tableName = "local_table")
data class localNews(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description")val desc: String,
    @SerializedName("url") val url: String,
    @SerializedName("name") val sourceName: String,
    @SerializedName("urlToImage")val urlImage: String,
    @SerializedName("publishedAt")val pubAt: String
)

@Entity(tableName = "enter_table")
data class enterNews(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description")val desc: String,
    @SerializedName("url") val url: String,
    @SerializedName("name") val sourceName: String,
    @SerializedName("urlToImage")val urlImage: String,
    @SerializedName("publishedAt")val pubAt: String

)

@Entity(tableName = "sports_table")
data class sportsNews(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description")val desc: String,
    @SerializedName("url") val url: String,
    @SerializedName("name") val sourceName: String,
    @SerializedName("urlToImage")val urlImage: String,
    @SerializedName("publishedAt")val pubAt: String
)

@Entity(tableName = "tech_table")
data class techNews(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description")val desc: String,
    @SerializedName("url") val url: String,
    @SerializedName("name") val sourceName: String,
    @SerializedName("urlToImage")val urlImage: String,
    @SerializedName("publishedAt")val pubAt: String
)
