package com.example.peter.jetnewsguy.data

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {


//country =us
    @GET("v2/top-headlines")
    fun getTopNews( @Query("apiKey") key: String, @Query("country") country: String ):Deferred<Response<List<topNews>>>

    //county=ng
    @GET("v2/top-headlines")
    fun getLocalNews( @Query("apiKey") key:String, @Query("country") country: String):Deferred<Response<List<localNews>>>


    //cater=technology
    @GET("v2/top-headlines")
    fun getTechNews( @Query("apiKey") key:String, @Query("category") category: String):Deferred<Response<List<techNews>>>


    //cate=sports country=gb
    @GET("v2/top-headlines")
    fun getSportsNews( @Query("apiKey") key:String, @Query("category") category: String, @Query("country") country: String ):Deferred<Response<List<sportsNews>>>


    //cate=entertainment
    @GET("v2/top-headlines")
    fun getEnterNews( @Query("apiKey") key:String, @Query("category") category:String):Deferred<Response<List<enterNews>>>


}