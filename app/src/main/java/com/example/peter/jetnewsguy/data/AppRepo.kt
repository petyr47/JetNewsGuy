package com.example.peter.jetnewsguy.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Dao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class AppRepo(application: Application) {

    private val MenterDao: enterDao
    private val MsportsDao:sportsDao
    private val MtechDao: techDao
    private val MlocalDao:localDao
    private val MtopDao:topDao
//
//    var allTop: LiveData<List<topNews>>
//    var allTech: LiveData<List<techNews>>
//    var allSports: LiveData<List<sportsNews>>
//    var allLocal: LiveData<List<localNews>>
//    var allEnter: LiveData<List<enterNews>>


    init {
        val database = AppDatabase.getInstance(application)
        MenterDao=database.mEnterDao()
        MsportsDao=database.mSportsDao()
        MtechDao=database.mTechDao()
        MtopDao=database.mTopDao()
        MlocalDao=database.mLocalDao()
//
//        allTop=MtopDao.getAllItems()
//        allTech=MtechDao.getAllItems()
//        allSports=MsportsDao.getAllItems()
//        allLocal=MlocalDao.getAllItems()
//        allEnter=MenterDao.getAllItems()

    }


    fun getAndInsertData(){

        val service=RetroApi.makeApiService()

        CoroutineScope(Dispatchers.IO).launch {

            withContext(Dispatchers.Main){
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

                        MlocalDao.updateData(localNewsResult)
                        MtopDao.updateData(topNewsResult)
                        MtechDao.updateData(techNewsResult)
                        MsportsDao.updateData(sportsNewsResult)
                        MenterDao.updateData(enterNewsResult)


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


}