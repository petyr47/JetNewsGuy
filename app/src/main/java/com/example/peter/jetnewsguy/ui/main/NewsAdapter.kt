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


         fun fixDate(dateTime: String?): String {
             dateTime?.dropLast(1)
             val seperated = dateTime?.split('T')
             val date = seperated?.get(0)
             val time = seperated?.get(1)?.dropLast(4)

             return "$date, $time "
         }

         fun ImageView.glideLoad(url: String?) {

             Glide.with(context)
                 .load(url)
                 .placeholder(R.drawable.placeholder)
                 .into(this)

         }

    }

}

