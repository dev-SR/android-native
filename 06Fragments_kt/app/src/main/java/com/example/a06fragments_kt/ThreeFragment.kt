package com.example.a06fragments_kt

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a06fragments_kt.databinding.FragmentThreeBinding
import java.lang.ClassCastException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThreeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThreeFragment : Fragment() {
    private lateinit var fvb: FragmentThreeBinding

    interface MethodFromActivity {
        fun activityMethod()
    }

    var callback: MethodFromActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fvb = FragmentThreeBinding.inflate(inflater, container, false)
        return fvb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fvb.btnShow.setOnClickListener {
            //invoke the activity method
            callback?.activityMethod()
        }
    }

    //make sure Activity has `activityMethod`.

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as? MethodFromActivity
        /**
         * `Activity` class is passed as `context`.
         * if `MethodFromActivity` interface is implemented by Activity
         * then `context` - Activity can be casted to `MethodFromActivity`
         * */

        if (callback == null)
            throw ClassCastException("$context must implement MethodFromActivity")
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

}