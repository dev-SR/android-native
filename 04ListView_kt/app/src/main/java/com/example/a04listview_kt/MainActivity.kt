package com.example.a04listview_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.a04listview_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)

        val movieList: Array<Movie> = Movie.getMovieList()
        vb.lvMovies.adapter = MovieAdapter(this, movieList)
        // movieList.forEach { i -> Log.d("BTN", i.movie_name) }
        vb.lvMovies.setOnItemClickListener { parent, view, position, id ->
            val text = view.findViewById<TextView>(R.id.tvMovieName).text
            Toast.makeText(this, "Movie: $text, Pos: ${position + 1} ", Toast.LENGTH_SHORT)
                .show()
        }


//        vb.lvMovies.adapter = ArrayAdapter(
//            this,
//            R.layout.fruit_item,
//            R.id.tvFruitName,
//            arrayOf(
//                "Apple",
//                "Mango",
//                "Guava",
//                "Banana",
//                "Kiwi",
//                "Grapes",
//                "Watermelon",
//                "Melon",
//                "Pineapple",
//                "Papaya"
//            )
//        )

    }
}