package com.example.peter.jetnewsguy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.peter.jetnewsguy.ui.main.*
import com.google.android.material.snackbar.Snackbar
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

        getSupportFragmentManager().beginTransaction().replace(R.id.container, arrayFrag[newInitialPosition]).commit();

        bottom_nav_view.setNavigationChangeListener { _, position ->
            val frag=arrayFrag[position]

            getSupportFragmentManager().beginTransaction().replace(R.id.container, frag).commit();
        }


        bottom_nav_view.setCurrentActiveItem(newInitialPosition)

        bar_image.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)

        }
    }

}
