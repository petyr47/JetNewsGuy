package com.example.peter.jetnewsguy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.peter.jetnewsguy.ui.main.*
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private val arrayFrag= arrayListOf(

        NewsFragment("local", "Local News", R.drawable.local_gradient ),
        NewsFragment("sports", "Sports News", R.drawable.sports_gradient),
        NewsFragment("top", "Top News", R.drawable.top_gradient),
        NewsFragment("entertainment", "Entertainment News", R.drawable.enter_gradient),
        NewsFragment("tech", "Tech News", R.drawable.tech_gradient)
    )

    val newInitialPosition = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


        card_bar_title.text="News Guy"
        card_bar.setBackgroundResource(android.R.color.background_light)

        card_bar.setBackgroundResource(R.drawable.top_gradient)

        getSupportFragmentManager().beginTransaction().replace(R.id.container, arrayFrag[newInitialPosition]).commit();


        bottom_nav_view.setNavigationChangeListener { _, position ->
            val frag=arrayFrag[position]

            getSupportFragmentManager().beginTransaction().replace(R.id.container, frag).commit();
        }


        bottom_nav_view.setCurrentActiveItem(newInitialPosition)
    }

}
