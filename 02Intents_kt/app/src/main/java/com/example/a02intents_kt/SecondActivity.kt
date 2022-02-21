package com.example.a02intents_kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a02intents_kt.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var vb: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivitySecondBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)

        vb.btnSecond.setOnClickListener {
            val intent = Intent(this, ExplicitActivity::class.java)
            startActivity(intent)
        }

    }
}