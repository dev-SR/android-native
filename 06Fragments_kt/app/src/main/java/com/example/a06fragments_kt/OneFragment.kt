package com.example.a06fragments_kt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.a06fragments_kt.databinding.FragmentOneBinding


class OneFragment : Fragment() {
    private lateinit var fvb: FragmentOneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fvb = FragmentOneBinding.inflate(inflater, container, false)
        return fvb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fvb.btnShowAct.setOnClickListener {

            val data = "YYYYYY"
            //getActivity()
            activity?.let {
                (it as MainActivity).showActivityMessage(data)
            }

        }
    }

    fun showFragmentMessage(data: String?) {
        activity?.let {
            Toast.makeText(it, "This message is from Fragment\nData sent from Activity:$data", Toast.LENGTH_SHORT)
                .show()
        }
    }

}