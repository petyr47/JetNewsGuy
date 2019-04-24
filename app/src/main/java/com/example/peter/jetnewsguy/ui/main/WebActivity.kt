package com.example.peter.jetnewsguy.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.bumptech.glide.Glide
import com.example.peter.jetnewsguy.R
import kotlinx.android.synthetic.main.activity_web.*


class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val url = intent.getStringExtra("link")


        Glide.with(this)
            .load(R.drawable.monkeyplace)
            .into(image_view)

        val webView=web_view as WebView



        webView.webViewClient=object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                image_view.visibility=View.GONE
            }
        }
        webView.settings.javaScriptEnabled=true


        if(url !=null){
            webView.loadUrl(url)
        }
 }

}
