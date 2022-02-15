package com.example.a01eventhandler_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
//
//        vb.btnSubmit.setOnClickListener {
//            var name = vb.etName.text.toString();
//            Toast.makeText(applicationContext, name, Toast.LENGTH_LONG).show()
//        }
//        vb.btnSubmit.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(p0: View?) {
//                var name = vb.etName.text.toString();
//                Toast.makeText(applicationContext, name, Toast.LENGTH_LONG).show()
//            }
//        })
//
//        // using Scope functions - with
//        with(vb) {
//            btnSubmit.setOnClickListener {
//                var name = vb.etName.text.toString();
//                Toast.makeText(applicationContext, name, Toast.LENGTH_LONG).show()
//            }
//        }
        // using Scope functions - apply
        vb.apply {
            btnSubmit.setOnClickListener {
                var name = vb.etName.text.toString();
                Toast.makeText(applicationContext, name, Toast.LENGTH_LONG).show()
            }
        }

    }
}
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        val etName = findViewById<EditText>(R.id.etName)
//        val btnSubmit: Button = findViewById(R.id.btnSubmit)
//
//        //Using object - anonymous inner class
//        btnSubmit.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(p0: View?) {
//                var name = etName.text.toString();
//                Toast.makeText(applicationContext, name, Toast.LENGTH_LONG).show()
//            }
//
//        })
//
//
//        //Using lambda
//        btnSubmit.setOnClickListener {
//            TODO("Not yet implemented")
//        }
//
//        //Using scope function
//        with(btnSubmit) {
//
//            setOnClickListener {
//                TODO("Not yet implemented")
//            }
//        }
//        //or
//        btnSubmit.apply {
//            setOnClickListener {
//                TODO("Not yet implemented")
//            }
//        }
//    }
//}