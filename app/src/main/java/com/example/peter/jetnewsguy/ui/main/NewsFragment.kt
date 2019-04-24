package com.example.peter.jetnewsguy.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.peter.jetnewsguy.R
import com.example.peter.jetnewsguy.data.Article
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.news_list.*

class NewsFragment(private val type: String, private val head:String,private val cardBack:Int):Fragment() {
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.news_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.card_bar_title?.text=head
        activity?.card_bar?.setBackgroundResource(cardBack)

        viewModel= ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.getNews(type).observe(this, Observer {makeList(it)} )


        list_view.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy>0 || dy<0){
                    activity?.card_bar_container?.visibility=View.GONE
                   activity?.bottom_nav_view?.visibility=View.GONE

                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState==RecyclerView.SCROLL_STATE_IDLE){
                    activity?.card_bar_container?.visibility=View.VISIBLE
                    activity?.bottom_nav_view?.visibility=View.VISIBLE
                }

            }
        })

        if (!viewModel.checkInternet()){
            val message="You are using News Guy in Offline Mode, \n Most Features will not be availible till you are online!"
            Snackbar.make(news_frame as View, message, Snackbar.LENGTH_LONG).show()

        }

    }

    private fun makeList(list: List<Article>){
        list_view.layoutManager= LinearLayoutManager(activity?.applicationContext)
        val newsAdapter=NewsAdapter(list, activity?.applicationContext!!)
        list_view.adapter=newsAdapter
    }

}