package com.example.mirrorcontroller

import android.util.Log
import android.view.MotionEvent
import android.view.View

class TouchController (){
    companion object touchHandler : View.OnTouchListener{
        override fun onTouch(target: View?, motionEvent: MotionEvent?): Boolean {
            if(target?.id == R.id.test_id ){
                val newPosX  = clampXCoordinate(target?.x + motionEvent!!.x - (target.width/2), target.width)
                val newPosY  = clampYCoordinate(target?.y + motionEvent!!.y - (target.height/2), target.height)
                Log.d("KIKKEL", "target id is ${target.id}")
                target?.x = newPosX
                target?.y = newPosY
            }
            return true
        }

        fun clampXCoordinate (value: Float, w: Int) : Float =  Math.min(DeviceInformation.windowWidth - w, Math.max(0, value.toInt())).toFloat()
        fun clampYCoordinate (value: Float, h: Int) : Float =  Math.min(DeviceInformation.windowHeight - h, Math.max(0, value.toInt())).toFloat()
    }
}