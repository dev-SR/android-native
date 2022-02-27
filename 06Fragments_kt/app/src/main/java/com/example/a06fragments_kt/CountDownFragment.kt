package com.example.a06fragments_kt

import android.accounts.AbstractAccountAuthenticator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.a06fragments_kt.databinding.FragmentCountDownBinding


class CountDownFragment : Fragment() {
    private lateinit var fvb: FragmentCountDownBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fvb = FragmentCountDownBinding.inflate(inflater, container, false)
        return fvb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var count = 0
        fvb.btnMin.setOnClickListener {
            fvb.tvMin.text = (--count).toString()
            Toast.makeText(requireContext(), "value: $count", Toast.LENGTH_SHORT).show()
//            Toast.makeText(view.context, "value: $count", Toast.LENGTH_SHORT).show()

        }
    }
}