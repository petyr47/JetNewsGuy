package com.example.peter.jetnewsguy.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.peter.jetnewsguy.data.AppRepo
import com.example.peter.jetnewsguy.data.Article

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repo: AppRepo
    private var news:List<Article>

    init {
        repo = AppRepo(application)
        repo.getAndInsertData()

        news=repo.retrieveFromRoom("top")

        Log.e("View Model", "Init ran")
    }

}




