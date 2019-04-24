package com.example.peter.jetnewsguy.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.peter.jetnewsguy.R
import com.example.peter.jetnewsguy.data.Article
import kotlinx.android.synthetic.main.news_item.view.*
import java.text.SimpleDateFormat
import android.text.format.DateUtils
import java.util.*


class NewsAdapter (val list: List<Article>, val context: Context): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item, parent, false))



    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentArticle = list[position]

        holder.bindNews(currentArticle)
    }


     class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

         private var url: String? =null

         override fun onClick(v: View?) {
             val context=itemView.context
             val intent=Intent(context, WebActivity::class.java)
             intent.putExtra("link", url)
             context.startActivity(intent)
         }

         init {
             itemView.setOnClickListener(this)
         }

          fun bindNews(article: Article){
             val time = fixDate(article.publishedAt)

             itemView.news_title?.text = article.title
             itemView.news_time?.text =time
             url=article.url
             itemView.news_image.glideLoad(article.urlToImage)


         }


         private fun fixDate(dateTime: String?): String = timeAgo(dateTime)


         private fun ImageView.glideLoad(url: String?) {

             Glide.with(context)
                 .load(url)
                 .placeholder(com.example.peter.jetnewsguy.R.drawable.placeholder)
                 .into(this)

         }


         private fun timeAgo(postedAt: String?): String{

             val  inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())

             val date = inputFormat.parse(postedAt)

             val niceDateStr = DateUtils.getRelativeTimeSpanString(
                 date.time,
                 Calendar.getInstance().timeInMillis,
                 DateUtils.MINUTE_IN_MILLIS
             )
             return  niceDateStr.toString()
         }

    }


}

