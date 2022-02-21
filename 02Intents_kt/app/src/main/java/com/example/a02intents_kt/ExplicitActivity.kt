package com.example.a02intents_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a02intents_kt.databinding.ActivityExplicitBinding
import com.example.a02intents_kt.databinding.ActivityImplicitBinding

class ExplicitActivity : AppCompatActivity() {
    private lateinit var vb: ActivityExplicitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityExplicitBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)    }
}