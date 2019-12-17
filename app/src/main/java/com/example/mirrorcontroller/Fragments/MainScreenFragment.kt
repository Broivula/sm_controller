package com.example.mirrorcontroller.Fragments


import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.text.method.Touch
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mirrorcontroller.*

import kotlinx.android.synthetic.main.fragment_main_screen.*
import org.jetbrains.anko.attr
import org.jetbrains.anko.childrenRecursiveSequence
import org.jetbrains.anko.custom.style
import org.jetbrains.anko.sdk27.coroutines.onTouch
import org.jetbrains.anko.textColor

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



        val list =  Database.getListOfElements()

        val element : TextElement = TextElement(
            list[0]?.getContent(),
            list[0]!!.getElementType(),
            list[0]!!.getPosX(),
            list[0]!!.getPosY(),
            context,
            list[0]!!.getUID()

        )

        element.text =  list[0]?.getContent()
        element.textSize = 25f
        element.setTextColor(Color.WHITE)
        element.x =  list[0]?.getPosX()!!.toFloat()
        element.y =  list[0]?.getPosY()!!.toFloat()
        (view as ViewGroup).addView(element)

        /*

            Log.d("KIKKEL", "state of eleme $element")
            element.text = it?.getContent()
            element.textSize = 25f
            element.setTextColor(Color.WHITE)
            element.x = it?.getPosX()!!.toFloat()
            element.y = it?.getPosY()!!.toFloat()
            (view as ViewGroup).addView(element)
        }


        val test  = TextElement("Something else", ElementType.PLAIN_TEXT, 200, 200, context)



        test.text = test._content
        test.setTextColor(Color.CYAN)
        test.textSize = 25f
        test.x = test._posX.toFloat()
        test.y = test._posY.toFloat()


        (view as ViewGroup).addView(test)
         */
        view.childrenRecursiveSequence().iterator().forEach {
            Log.d("KIKKEL", "current view is $it")
            it.setOnTouchListener(TouchController.touchHandler)
        }



    }

}
