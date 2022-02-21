package com.example.a03dynamicviews_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.a03dynamicviews_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)
        var show = false
        vb.button.setOnClickListener {
            if (!show) {
                // add view
                var rootView = findViewById<LinearLayout>(R.id.rootLayout)
                val inflater = LayoutInflater.from(this)
                var footerView =
                    inflater.inflate(R.layout.inflate, rootView, false)
                // make changes to our custom layout and its subviews
                // get subview from `inflate.xml`
                val tvFooter: TextView = footerView.findViewById(R.id.tvFooter)
                tvFooter.setBackgroundColor(resources.getColor(R.color.purple_500))
                tvFooter.text = "New Footer"
                rootView.addView(footerView)

                show = true
            } else {
                // remove view
                vb.rootLayout.removeView(findViewById(R.id.inflateView))
                show = false
            }
        }
    }
}