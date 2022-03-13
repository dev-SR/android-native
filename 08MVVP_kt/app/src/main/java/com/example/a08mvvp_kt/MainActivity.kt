package com.example.a08mvvp_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.a08mvvp_kt.databinding.ActivityMainBinding

// State Management without viewModel
//class MainActivity : AppCompatActivity() {
//    private lateinit var vb: ActivityMainBinding
//    private var count = 0
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        vb = ActivityMainBinding.inflate(layoutInflater)
//        val view = vb.root
//        setContentView(view)
//        vb.tvShow.text = count.toString();
//        vb.btnAdd.setOnClickListener {
//            count++
//            vb.tvShow.text = count.toString()
//        }
//    }
//}
// State Management With ViewModel
class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding

    //    private var count = 0
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)
        /**Associate the UI Controller and ViewModel*/
        val dataRepo = 10 // Data source
        val factory = MainActivityViewModel.Factory(dataRepo) // Factory
        viewModel =
            ViewModelProvider(this, factory).get(MainActivityViewModel::class.java) // ViewModel
//        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java);

        /**Use the ViewModel in your UI Controller*/
        //vb.tvShow.text = count.toString();
//        vb.tvShow.text = viewModel.count.toString();
//        vb.btnAdd.setOnClickListener {
//            /**Use the ViewModel in your UI Controller*/
//            //count++
//            //vb.tvShow.text = count.toString()
//            vb.tvShow.text = viewModel.getUpdatedCounter().toString()
//        }
        viewModel.getCountValue().observe(this) { count ->
            vb.tvShow.text = count.toString()
        }
        vb.btnAdd.setOnClickListener {
            viewModel.updateCounter()
        }
    }
}