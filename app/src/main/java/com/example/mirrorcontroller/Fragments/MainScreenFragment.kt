package com.example.mirrorcontroller.Fragments


import android.graphics.Point
import android.os.Bundle
import android.text.method.Touch
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment

import com.example.mirrorcontroller.R
import com.example.mirrorcontroller.TouchController
import kotlinx.android.synthetic.main.fragment_main_screen.*
import org.jetbrains.anko.childrenRecursiveSequence
import org.jetbrains.anko.sdk27.coroutines.onTouch

/**
 * A simple [Fragment] subclass.
 */
class MainScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        view.setOnTouchListener(TouchController.touchHandler)
        view.childrenRecursiveSequence().iterator().forEach {
            Log.d("KIKKEL", "current view is $it")
            it.setOnTouchListener(TouchController.touchHandler)
        }
        val displayMetrics  = DisplayMetrics()
        view.display.getMetrics(displayMetrics)
        val w = displayMetrics.widthPixels
        val h = displayMetrics.heightPixels


        /*
        test_id.setOnClickListener {
            Log.d("KIKKEL", it.bottom.toString())
            Log.d("KIKKEL", it.left.toString())
            Log.d("KIKKEL", it.pivotX.toString())
            Log.d("KIKKEL", it.pivotY.toString())
            Log.d("KIKKEL", it.x.toString())
            Log.d("KIKKEL", it.y.toString())
            Log.d("KIKKEL", "current width $w and height $h")
        }
        */

    }

}
