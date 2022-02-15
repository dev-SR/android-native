package com.example.a01eventhandler_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.a01eventhandler_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Names _, __, ___, ..., are reserved in Kotlin
    private lateinit var vb: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)

        // 1
        vb.btnSubmit.setOnClickListener {
            var name = vb.etName.text.toString();
            Toast.makeText(applicationContext, name, Toast.LENGTH_LONG).show()
        }

        // 2. using Scope functions - with
        with(vb) {
            btnSubmit.setOnClickListener {
                var name = vb.etName.text.toString();
                Toast.makeText(applicationContext, name, Toast.LENGTH_LONG).show()
            }
        }
        // 3. using Scope functions - apply
        vb.apply {
            btnSubmit.setOnClickListener {
                var name = vb.etName.text.toString();
                Toast.makeText(applicationContext, name, Toast.LENGTH_LONG).show()
            }
        }


    }
}