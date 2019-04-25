package com.example.peter.jetnewsguy.ui.main

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import com.example.peter.jetnewsguy.R
import android.view.animation.TranslateAnimation




fun View.slideUp(context: Context?){
    val slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up)

    if (this.visibility == View.INVISIBLE){
        this.visibility= View.VISIBLE
        this.startAnimation(slideUp)
    }
}

fun View.slideDown(context: Context?){
    val slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down)

    if (this.visibility == View.VISIBLE){
        this.startAnimation(slideDown)
        this.visibility = View.INVISIBLE
    }
}
fun View.slideDownCard(){

    if (this.visibility == View.INVISIBLE){
        val animate = TranslateAnimation(
            0f, // fromXDelta
            0f, // toXDelta
            -2.0f *this.height,
            0f// fromYDelta

        )                // toYDelta
        animate.duration = 500
        animate.fillAfter = true
        this.startAnimation(animate)
        this.setVisibility(View.VISIBLE)
    }
}

fun View.slideUpCard(){


if (this.visibility == View.VISIBLE){
    val animate = TranslateAnimation(
        0f, // fromXDelta
        0f, // toXDelta
        0f, // fromYDelta
        -2.0f *this.height
    )                // toYDelta
    animate.duration = 500
    animate.fillAfter = true
    this.startAnimation(animate)
    this.setVisibility(View.INVISIBLE)
}


}


fun View.slideOut(context: Context?){
    val slideOut= AnimationUtils.loadAnimation(context, R.anim.slide_out)

    if (this.visibility == View.VISIBLE){
        this.startAnimation(slideOut)
        this.visibility= View.INVISIBLE
    }
}

fun View.slideIn(context: Context?){
    val slideIn = AnimationUtils.loadAnimation(context, R.anim.slide_in)

    if (this.visibility == View.INVISIBLE){
        this.visibility = View.VISIBLE
        this.startAnimation(slideIn)
    }
}