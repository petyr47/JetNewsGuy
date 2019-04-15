package com.example.peter.jetnewsguy.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.peter.jetnewsguy.R
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

        GlobalScope.launch (Dispatchers.Main){
            Toast.makeText(activity?.applicationContext, "Hello it has started", Toast.LENGTH_SHORT).show()
            delay(10000)
            message.text = "I have changed after 10 seconds"
        }

    }
//
//    fun loadDataAsync() = async(UI) {
//        try {
//            //Turn on busy indicator.
//            val job = async(CommonPool) {
//                //We're on a background thread here.
//                //Execute blocking calls, such as retrofit call.execute().body() + caching.
//            }
//            job.await();
//            //We're back on the main thread here.
//            //Update UI controls such as RecyclerView adapter data.
//        }
//        catch (e: Exception) {
//        }
//        finally {
//            //Turn off busy indicator.
//        }

}
