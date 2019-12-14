package com.example.mirrorcontroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import com.example.mirrorcontroller.Network_Operations.NetworkSocket
import kotlinx.android.synthetic.main.activity_main.*
import java.net.Socket

class MainActivity : AppCompatActivity() {


    private lateinit var socket: Socket

    private val messageHandler : Handler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message?) {
            if(msg?.what == 0){
              Log.d("KIKKEL", "request: ${msg.obj.toString()}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myRunnable = NetworkSocket(this)
        val myThread = Thread(myRunnable)
        myThread.start()


        test_button.setOnClickListener { view -> myRunnable.socket.emit("new_action", "{ data: 5 }") }

    }

}
