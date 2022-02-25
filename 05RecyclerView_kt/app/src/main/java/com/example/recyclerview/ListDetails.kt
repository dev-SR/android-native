package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recyclerview.databinding.ActivityListDetailsBinding

class ListDetails : AppCompatActivity() {
    private lateinit var vb: ActivityListDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityListDetailsBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)

        val name = intent.getStringExtra("name")
        val imgSrc = intent.getIntExtra("img", R.drawable.finding_nemo)

        name?.let {
            vb.tvShowMovieList.text = name
            vb.imgvShowPoster.setImageResource(imgSrc)

        }

    }
}