package com.example.a09firebase_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a09firebase_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)
//              vb.
    }
}