package com.example.mirrorcontroller

import android.content.Context
import android.util.Log
import com.example.mirrorcontroller.Network_Operations.NetworkSocket
import kotlinx.coroutines.runBlocking

object SetupObject {

    fun setup (context: Context, networkCallback: () -> Unit) = runBlocking {

        // first, let's deal with the server connection
        // check if the socket exists, if not, establish connection

        if(NetworkSocket.socket == null)
        {
            NetworkSocket.establishConnection(context, networkCallback)
        }






    }

}