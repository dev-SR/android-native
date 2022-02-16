package com.example.a02intents_kt

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a02intents_kt.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)

        vb.btnEmail.setOnClickListener {
            val email = vb.etEmail.text.toString()
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("mailto:$email")
            i.putExtra(Intent.EXTRA_SUBJECT, "Implicit Intents")
            startActivity(i)
        }

    }
}