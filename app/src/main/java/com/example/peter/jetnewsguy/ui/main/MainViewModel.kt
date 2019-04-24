package com.example.peter.jetnewsguy.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.peter.jetnewsguy.data.AppRepo
import com.example.peter.jetnewsguy.data.Article
import com.example.peter.jetnewsguy.data.NewsPerferences
import kotlinx.coroutines.*
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repo: AppRepo
    private lateinit var news:MutableLiveData<List<Article>>

    private var job= Job()
    private val scope=  CoroutineScope(job +Dispatchers.IO)

    init {
        repo = AppRepo(application)
        if (shouldSync()){repo.getAndInsertData()}
    }

    fun getNews(category: String):LiveData<List<Article>>{
        if (!::news.isInitialized){
            news= MutableLiveData()

            scope.launch(Dispatchers.IO){
                news.postValue(repo.retrieveFromRoom(category))

            }
        }
        return news
    }

    private fun shouldSync(): Boolean{
        val interval =1 * 3600 * 1000
        val lastSync= NewsPerferences.getEllapsedTimeSinceLastSync(getApplication())

        if (lastSync>= interval){
            return true
        }
        return false
    }

    fun checkInternet():Boolean{
        var result= true

        CoroutineScope(Dispatchers.IO).launch {
            result = try {
                val socket=Socket()
                socket.connect(InetSocketAddress("www.google.com", 80), 5000)
                true
            } catch (e: IOException){
                false
            }
        }

        Log.e("internet testing", "Test failed")

        return result


    }


}




