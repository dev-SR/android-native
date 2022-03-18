package com.example.a07navigation_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.a07navigation_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)
        vb.lv.adapter = ArrayAdapter(
            this,
            R.layout.single_textview_layout,
            R.id.textView,
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
                "Papaya",
                "Kiwi",
                "Grapes",
                "Watermelon",
                "Melon",
                "Pineapple",
                "Papaya"
            )
        )
    }
}