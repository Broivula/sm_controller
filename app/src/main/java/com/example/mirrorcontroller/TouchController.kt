package com.example.mirrorcontroller

import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.mirrorcontroller.Network_Operations.API.POST
import java.sql.Time

class TouchController (){
    companion object touchHandler : View.OnTouchListener{

        override fun onTouch(target: View?, motionEvent: MotionEvent?): Boolean {

            if((target as? TextElement)?._elementType == ElementType.PLAIN_TEXT ){
                val newPosX  = clampXCoordinate(target?.x + motionEvent!!.x - (target.width/2), target.width)
                val newPosY  = clampYCoordinate(target?.y + motionEvent!!.y - (target.height/2), target.height)
                target?.x = newPosX
                target?.y = newPosY
                target._posX = newPosX.toInt()
                target._posY = newPosY.toInt()
                POST.postData(target.toString())

                // when finger is lifted, save the data to the database and local state
                if(motionEvent.action == MotionEvent.ACTION_UP){
                   Database.updateLocalElementStatePosition(target)
                    Database.updateDatabaseElementState(target)
                }
            }


            /*
            val list = Database.getListOfElements()
            list.forEach {
                Log.d("KIKKEL", "members: ${it.toString()}")
            }
            */


            return true
        }

        fun clampXCoordinate (value: Float, w: Int) : Float =  Math.min(DeviceInformation.windowWidth - w, Math.max(0, value.toInt())).toFloat()
        fun clampYCoordinate (value: Float, h: Int) : Float =  Math.min(DeviceInformation.windowHeight - h, Math.max(0, value.toInt())).toFloat()
    }
}