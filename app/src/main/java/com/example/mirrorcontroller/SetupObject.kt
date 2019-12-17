package com.example.mirrorcontroller

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import com.example.mirrorcontroller.Network_Operations.NetworkSocket
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object SetupObject {

    fun setup (context: Context, networkCallback: () -> Unit, view: View) = runBlocking {

        // first, let's deal with the server connection
        // check if the socket exists, if not, establish connection

        Log.d("KIKKEL", "setup begins 1")
        if(NetworkSocket.socket == null)
        {
            NetworkSocket.establishConnection(context)
        }

        Log.d("KIKKEL", "setup continues after socket and initializes database,  3")

        // next, let's set up the database ready to use.

        launch {
            if(Database.checkIfDatabaseExists() == null){
                Database.initDatabase(context)
            }
        }




        // set up the device information into
        DeviceInformation.windowWidth = view.width
        DeviceInformation.windowHeight = view.height

        launch {
            Log.d("KIKKEL", "database initialized, all done! 5")
            networkCallback()
        }

    }

}