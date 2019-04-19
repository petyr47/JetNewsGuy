package com.example.peter.jetnewsguy.data

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {


    @GET("v2/top-headlines")
    fun getTopNews( @Query("apiKey") key: String, @Query("country") country: String ):Deferred<Response<Example>>

    @GET("v2/top-headlines")
    fun getLocalNews( @Query("apiKey") key:String, @Query("country") country: String):Deferred<Response<Example>>


    @GET("v2/top-headlines")
    fun getTechNews( @Query("apiKey") key:String, @Query("category") category: String):Deferred<Response<Example>>


    @GET("v2/top-headlines")
    fun getSportsNews( @Query("apiKey") key:String, @Query("category") category: String, @Query("country") country: String ):Deferred<Response<Example>>


    @GET("v2/top-headlines")
    fun getEnterNews( @Query("apiKey") key:String, @Query("category") category:String):Deferred<Response<Example>>


}