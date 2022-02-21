package com.example.a02intents_kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a02intents_kt.databinding.ActivityExplicitBinding
import com.example.a02intents_kt.databinding.ActivityImplicitBinding

const val KEY_1 = "Name"
const val KEY_2 = "Age"
const val KEY_3 = "isStudent"

class ExplicitActivity : AppCompatActivity() {
    private lateinit var vb: ActivityExplicitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityExplicitBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)

        vb.btnFirst.setOnClickListener {
            val name: String = "jhon"
            val age: Int = 22
            val isStudent: Boolean = false
//            val intent = Intent(this, SecondActivity::class.java)
//            intent.putExtra(KEY_1, name)
//            intent.putExtra(KEY_2, age)
//            intent.putExtra(KEY_3, isStudent)
            //or
            val intent = Intent().apply {
                putExtra(KEY_1, name)
                putExtra(KEY_2, age)
                putExtra(KEY_3, isStudent)
            }
            startActivity(intent)
        }
    }
}