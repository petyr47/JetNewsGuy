package com.example.peter.jetnewsguy.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import retrofit2.HttpException

class AppRepo(application: Application) {

    private val newsDao: NewsDao

    init {
        val database = AppDatabase.getInstance(application)
        newsDao=database.newsDao()
    }


    fun getAndInsertData(){

        val service=RetroApi.makeApiService()

        CoroutineScope(Dispatchers.IO).launch {

            withContext(Dispatchers.IO){
                try {
                    val topNewsRequest= service.getTopNews("564ebf9dfec4409fbdaf1327e47c1932","us")
                    val sportsNewsRequest=service.getSportsNews("564ebf9dfec4409fbdaf1327e47c1932", "sports", "gb")
                    val techNewsRequest=service.getTechNews("564ebf9dfec4409fbdaf1327e47c1932", "technology")
                    val enterNewsRequest=service.getEnterNews("564ebf9dfec4409fbdaf1327e47c1932", "entertainment")
                    val localNewsRequest=service.getLocalNews("564ebf9dfec4409fbdaf1327e47c1932", "ng")

                    if (topNewsRequest.await().isSuccessful){

                        val topNewsResult=topNewsRequest.await().body()
                        val sportsNewsResult=sportsNewsRequest.await().body()
                        val techNewsResult=techNewsRequest.await().body()
                        val enterNewsResult=enterNewsRequest.await().body()
                        val localNewsResult=localNewsRequest.await().body()

                        newsDao.deleteAll()

                        var topNewsArticles=makeArticle(topNewsResult)
                        var sportsNewsArticles=makeArticle(sportsNewsResult)
                        var techNewsArticles=makeArticle(techNewsResult)
                        var enterNewsArticles=makeArticle(enterNewsResult)
                        var localNewsArticles =makeArticle(localNewsResult)

                        if (!topNewsArticles.isNullOrEmpty()){
                            topNewsArticles=fixCategory(topNewsArticles, "top")
                        }
                        if (!sportsNewsArticles.isNullOrEmpty()){
                            sportsNewsArticles=fixCategory(sportsNewsArticles, "sports")
                        }
                        if (!techNewsArticles.isNullOrEmpty()){
                            techNewsArticles=fixCategory(techNewsArticles, "tech")
                        }
                        if (!enterNewsArticles.isNullOrEmpty()){
                            enterNewsArticles=fixCategory(enterNewsArticles, "entertainment")
                        }
                        if (!localNewsArticles.isNullOrEmpty()){
                            localNewsArticles=fixCategory(localNewsArticles, "local")

                        }

                        newsDao.insertAll(topNewsArticles!!)
                        newsDao.insertAll(sportsNewsArticles!!)
                        newsDao.insertAll(techNewsArticles!!)
                        newsDao.insertAll(enterNewsArticles!!)
                        newsDao.insertAll(localNewsArticles!!)
                    }

                    else{
                        Log.e("App Repo", "Retrofit Network requests failed or at least topnews failed")
                    }

                }
                catch (e:HttpException){
                    Log.e("App Repo Catch Block", e.message())
                }
            }
        }
    }

    private fun fixCategory(news:List<Article>, category: String):List<Article>{

        Log.e("Fix cat", "lenght of raw list"+news.size)
        for (new in news){
            new.category=category
        }
        return news
    }

    private fun makeArticle(list :Example?):List<Article>? =list?.articles

     fun retrieveFromRoom(key: String):
             List<Article> = runBlocking{

         newsDao.getNews(key)
     }

}