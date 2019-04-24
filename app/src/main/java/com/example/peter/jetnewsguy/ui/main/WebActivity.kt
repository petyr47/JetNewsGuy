package com.example.peter.jetnewsguy.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.airbnb.lottie.LottieDrawable
import com.example.peter.jetnewsguy.R
import kotlinx.android.synthetic.main.activity_web.*


class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val url = intent.getStringExtra("link")


        val webView=web_view as WebView

        lottieAnimationView.setAnimation(R.raw.cycleanimation)
        lottieAnimationView.playAnimation()
        lottieAnimationView.repeatMode= LottieDrawable.RESTART
        lottieAnimationView.speed=2.5f


        webView.webViewClient=object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                lottieAnimationView.visibility=View.GONE
            }
        }

        webView.webChromeClient=object : WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if(newProgress>= 80){
                    lottieAnimationView.visibility=View.GONE
                }
            }
        }
        webView.settings.javaScriptEnabled=true
        webView.settings.cacheMode=WebSettings.LOAD_CACHE_ELSE_NETWORK

        if(url !=null) webView.loadUrl(url)
 }

}
