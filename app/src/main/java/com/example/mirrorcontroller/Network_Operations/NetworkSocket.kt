package com.example.mirrorcontroller.Network_Operations

import android.content.Context
import android.util.Log
import com.example.mirrorcontroller.R
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import java.io.*
import java.security.KeyStore
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import javax.net.ssl.*

class NetworkSocket(var app_context: Context) : Runnable {

    lateinit var socket: Socket

    override fun run() {

        val cf: CertificateFactory = CertificateFactory.getInstance("X.509")
        val caInput: InputStream = app_context.resources.openRawResource(R.raw.ca)
        val ca : X509Certificate = caInput.use{
            cf.generateCertificate(it) as X509Certificate
        }
        Log.d("KIKKEL", "ca= ${ca.subjectDN}")

        val keyStoreType = KeyStore.getDefaultType()
        val keyStore = KeyStore.getInstance(keyStoreType).apply {
            load(null, null)
            setCertificateEntry("ca", ca)
        }

        val tmfAlgorithm: String = TrustManagerFactory.getDefaultAlgorithm()
        val tmf: TrustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm).apply {
            init(keyStore)
        }


        val context : SSLContext = SSLContext.getInstance("TLS").apply {
            init(null, tmf.trustManagers, null)
        }


        val opts  = IO.Options()
        opts.port = 49256
        opts.sslContext = context
        opts.hostnameVerifier = NullHostNameVerifier()
        socket = IO.socket("https://192.168.8.102:3000", opts)
        socket.connect()



        socket.emit("new_action", "\"{ data: 5 }\"")



     //   conn.setHostnameVerifier(NullHostNameVerifier())
     //   conn.sslSocketFactory = context.socketFactory

        /*
        conn.requestMethod = "GET"
        val iStream : InputStream = conn.inputStream




        val text = iStream.bufferedReader().use{
            it.readText()
        }
        val result = StringBuilder()
        result.append(text).toString()
        val msg = myHandler.obtainMessage()
        msg.what = 0
        msg.obj = result
        myHandler.sendMessage(msg)

         */


    }
}

class NullHostNameVerifier : HostnameVerifier{
    override fun verify(p0: String?, p1: SSLSession?): Boolean {
        Log.d("KIKKEL", "verifying $p0")
        return true
    }
}
