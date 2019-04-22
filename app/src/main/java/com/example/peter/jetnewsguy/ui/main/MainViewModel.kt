package com.example.peter.jetnewsguy.ui.main

import android.app.Application
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.peter.jetnewsguy.data.AppRepo
import com.example.peter.jetnewsguy.data.Article
import com.example.peter.jetnewsguy.data.NewsDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repo: AppRepo
    private lateinit var news:MutableLiveData<List<Article>>

    private var job= Job()
    private val scope=  CoroutineScope(job +Dispatchers.IO)

    init {
        repo = AppRepo(application)
        repo.getAndInsertData()


        Log.e("View Model", "Init ran")
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




}




