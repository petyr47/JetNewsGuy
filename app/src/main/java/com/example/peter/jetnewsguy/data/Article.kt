package com.example.peter.jetnewsguy.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
@Entity(tableName = "news_table")
data class Article
    (
    @PrimaryKey(autoGenerate = true)
    val _id: Int? =null,

    @Json(name ="source")
    @Embedded
    var source: Source? =null,

    @Json(name ="author")
    var author: String? = null,

    @Json(name ="title")
    var title: String? = null,

    @Json(name ="description")
    var description: String? = null,

    @Json(name ="url")
    var url: String? = null,

    @Json(name ="urlToImage")
    var urlToImage: String? = null,

    @Json(name= "publishedAt")
    var publishedAt: String? = null,

    @Json(name="content")
    var content: String? = null,

    var category: String? = null

    )