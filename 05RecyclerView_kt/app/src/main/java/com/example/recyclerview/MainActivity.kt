package com.example.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var vb: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)

        val movieList = Movie.getMovieList(100)
        vb.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MovieAdapter(movieList) { movie ->
//                Toast.makeText(this@MainActivity, "$movie.movie_name, i:$pos", Toast.LENGTH_SHORT)
//                    .show()

                val intent = Intent(applicationContext, ListDetails::class.java).apply {
                    putExtra("name", movie.movie_name)
                    putExtra("img", movie.img_src)
                }
                startActivity(intent)
            }
        }
    }

    override fun onRestart() {
        super.onRestart()

    }
}