package com.example.mirrorcontroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.mirrorcontroller.Fragments.SwitchFragment
import com.example.mirrorcontroller.Network_Operations.NetworkSocket
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_loading.*
import java.net.Socket

class MainActivity : AppCompatActivity() {


    private lateinit var socket: Socket


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




       // test_button.setOnClickListener { view -> myRunnable.socket.emit("new_action", "{ data: 5 }") }

    }






}
