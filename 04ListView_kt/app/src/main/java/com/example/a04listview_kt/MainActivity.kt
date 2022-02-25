package com.example.a04listview_kt

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.a04listview_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding
    private lateinit var lastSelectedItem: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)

        val movieList: Array<Movie> = Movie.getMovieList()
        vb.lvMovies.adapter = MovieAdapter(movieList)
        vb.lvMovies.choiceMode = ListView.CHOICE_MODE_SINGLE
//        vb.lvMovies.setOnItemClickListener { parent, view, position, id ->
//            val text = view.findViewById<TextView>(R.id.tvMovieName).text
//            Toast.makeText(this, "Movie: $text, Pos: ${position + 1} ", Toast.LENGTH_SHORT)
//                .show()
//            // Change Color of All Clicked Item
////            view.setBackgroundColor(
////                ContextCompat.getColor(
////                    applicationContext,
////                    R.color.white
////                )
////            )
//            // Change Color of Only Currently Clicked Item
//            for (i in 0 until vb.lvMovies.childCount) {
//                if (position == i) {
//                    vb.lvMovies.getChildAt(i).setBackgroundColor(Color.rgb(157, 222, 252))
//                } else {
//                    vb.lvMovies.getChildAt(i).setBackgroundColor(Color.TRANSPARENT)
//                }
//            }
//
//        }


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