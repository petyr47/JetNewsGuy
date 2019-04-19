package com.example.peter.jetnewsguy.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Source (

    @Json(name="id")
    var id: String? = null,

    @Json(name ="name")
var name: String? = null

)



