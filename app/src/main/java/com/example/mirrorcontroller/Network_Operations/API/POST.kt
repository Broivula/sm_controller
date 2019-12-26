package com.example.mirrorcontroller.Network_Operations.API

import com.example.mirrorcontroller.API_URL
import com.example.mirrorcontroller.ElementClasses
import com.example.mirrorcontroller.Network_Operations.NetworkSocket
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.MultipartBody
import org.jetbrains.anko.doAsync


class POST {
    companion object PostToSocket{


        fun postDataToSocket(data: String){
            NetworkSocket.socket?.emit("new_action",data)
        }

        fun updateServerDatabase(data: String) {
            NetworkSocket.socket?.emit("update_db", data)
        }
    }
}