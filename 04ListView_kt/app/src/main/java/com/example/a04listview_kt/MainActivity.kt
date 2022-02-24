package com.example.a04listview_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        vb.lvFruits.adapter = ArrayAdapter(
            this,
            R.layout.fruit_item,
            R.id.tvFruitName,
            arrayOf(
                "Apple",
                "Mango",
                "Guava",
                "Banana",
                "Kiwi",
                "Grapes",
                "Watermelon",
                "Melon",
                "Pineapple",
                "Papaya"
            )
        )
        vb.lvFruits.setOnItemClickListener { parent, view, position, id ->
            val text = view.findViewById<TextView>(R.id.tvFruitName).text
            Toast.makeText(this, "Fruit: $text, Pos: $position ", Toast.LENGTH_SHORT)
                .show()
        }
    }
}