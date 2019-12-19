package com.example.mirrorcontroller.Network_Operations.API

import com.example.mirrorcontroller.ElementClasses
import com.example.mirrorcontroller.Network_Operations.NetworkSocket

class POST {
    companion object PostToSocket{
        fun postData(data: String){
            NetworkSocket.socket?.emit("new_action",data)
        }
    }
}