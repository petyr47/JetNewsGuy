package com.example.peter.jetnewsguy.data


import androidx.room.Embedded
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Example {

    @Json(name="status")
    var status: String? = null

    @Json(name="totalResults")
    var totalResults: Int? = null

    @Json(name ="articles")
    @Embedded//remove this if it does not work
    var articles: List<Article>? = null

}