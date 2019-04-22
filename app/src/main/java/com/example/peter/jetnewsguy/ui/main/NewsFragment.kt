package com.example.peter.jetnewsguy.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peter.jetnewsguy.R
import com.example.peter.jetnewsguy.data.Article
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.news_list.*

class NewsFragment(val type: String, val head:String, val cardBack:Int):Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)


        val rootView=inflater.inflate(R.layout.news_list, container, false)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.card_bar_title?.text=head
        activity?.card_bar?.setBackgroundResource(cardBack)

        val viewModel= ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.getNews(type).observe(
            this, Observer {
                makeList(it)

                Toast.makeText(activity?.applicationContext, it[4].title, Toast.LENGTH_SHORT).show()

            }
        )



    }

    fun makeList(list: List<Article>){

        list_view.layoutManager= LinearLayoutManager(activity?.applicationContext)
        val newsAdapter=NewsAdapter(list, activity?.applicationContext!!)

        list_view.adapter=newsAdapter

    }
}