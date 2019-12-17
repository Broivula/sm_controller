package com.example.mirrorcontroller

import android.util.Log
import android.view.MotionEvent
import android.view.View

class TouchController (){
    companion object touchHandler : View.OnTouchListener{
        override fun onTouch(target: View?, motionEvent: MotionEvent?): Boolean {
            if(target?.id == R.id.test_id){
                Log.d("KIKKEL", "${target.id}")
                target?.x = target?.x + motionEvent!!.x - (target.width/2)
                target?.y = target?.y + motionEvent!!.y - (target.height/2)
            }
            return true
        }
    }
}