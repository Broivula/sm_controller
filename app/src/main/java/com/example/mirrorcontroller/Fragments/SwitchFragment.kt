package com.example.mirrorcontroller.Fragments

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mirrorcontroller.R
import kotlinx.android.synthetic.main.activity_main.*

object SwitchFragment  {

    fun switch(option: String, supportFragmentManager: FragmentManager, enterAnimation: Int, exitAnimation: Int){

        val fManager = supportFragmentManager
        val fTransaction = fManager!!.beginTransaction()

        when(option){
            "loading_complete" -> {
                val fragment: Fragment = MainScreenFragment()
                fTransaction.setCustomAnimations(enterAnimation, exitAnimation)
                fTransaction.replace(R.id.fragment_container, fragment)
                fTransaction.commit()
            }
            else -> {
                Log.d("KIKKEL", "unknown event")
            }
        }

    }

}