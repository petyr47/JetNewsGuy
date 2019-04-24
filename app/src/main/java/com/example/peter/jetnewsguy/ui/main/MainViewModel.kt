package com.example.peter.jetnewsguy.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.peter.jetnewsguy.data.AppRepo
import com.example.peter.jetnewsguy.data.Article
import com.example.peter.jetnewsguy.data.NewsPerferences
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL

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

    fun shouldSync(): Boolean{
        val interval =1 * 3600 * 1000
        val lastSync= NewsPerferences.getEllapsedTimeSinceLastSync(getApplication())

        if (lastSync>= interval){
            NewsPerferences.saveNewLastSyncTime(getApplication(), System.currentTimeMillis())
            return true
        }
        return false
    }


}




