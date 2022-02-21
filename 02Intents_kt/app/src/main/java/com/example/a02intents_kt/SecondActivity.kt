package com.example.a02intents_kt

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a02intents_kt.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var vb: ActivitySecondBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivitySecondBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)

        // Retrieving data from intent
        val name: String? = intent.getStringExtra(KEY_1)
        val age: Int = intent.getIntExtra(KEY_1, 0)
        val isStudent: Boolean? = intent.getBooleanExtra(KEY_3, true)

        name?.let {
            vb.tvShow.text = "Name: $it\nAge: $age\nStudent: ${isStudent.toString()}"
        }


    }
}