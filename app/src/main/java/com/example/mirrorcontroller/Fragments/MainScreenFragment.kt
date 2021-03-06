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
import org.w3c.dom.Text

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

        createOnScreenElements(view)
    }


    private fun createOnScreenElements(view: View){

/*
        val t = TextElement("testing", ElementType.PLAIN_TEXT, 0.5f, 0.5f, context )
        val t2 = TextElement("kakkapylly", ElementType.PLAIN_TEXT, 0.3f, 0.3f, context )
        Database.addElementToDatabase(t)
        Database.addElementToDatabase(t2)

 */


        val list =  Database.getListOfElements()

        list.map {
           val ele = TextElement(
               it?.getContent(),
               it!!.getElementType(),
               it!!.getPosX(),
               it!!.getPosY(),
               context,
               it.getUID()
           )

            ele.text = ele._content
            ele.textSize = 25f
            ele.setTextColor(Color.WHITE)
            ele.x = ele._posX  * DeviceInformation.windowWidth
            ele.y = ele._posY * DeviceInformation.windowHeight
            (view as ViewGroup).addView(ele)
        }

        view.childrenRecursiveSequence().iterator().forEach {
            it.setOnTouchListener(TouchController.touchHandler)
        }
    }


}
