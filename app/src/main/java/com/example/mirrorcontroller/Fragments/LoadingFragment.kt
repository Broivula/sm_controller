package com.example.mirrorcontroller.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mirrorcontroller.ElementType
import com.example.mirrorcontroller.Network_Operations.NetworkSocket

import com.example.mirrorcontroller.R
import com.example.mirrorcontroller.SetupObject
import com.example.mirrorcontroller.TextElement
import kotlinx.android.synthetic.main.fragment_loading.*
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

/**
 * A simple [Fragment] subclass.
 */
class LoadingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("KIKKEL", "Fragment creation started..!")
        return inflater.inflate(R.layout.fragment_loading, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initiateLoading()

        Log.d("KIKKEL", "Fragment created!")
    }

    private fun initiateLoading() {
        rotateloading.start()


       thread { SetupObject.setup(context!!, {loadingCompleteCallback()}, this.view!!) }

    }

    fun loadingCompleteCallback(){

     //   val t = TextElement("test text!", ElementType.PLAIN_TEXT, 0, 0)
     //   val t2 = TextElement("another test !", ElementType.PLAIN_TEXT, 0, 0)
        SwitchFragment.switch("loading_complete", fragmentManager!!, R.anim.anim_slide_up, R.anim.anim_slide_up_out)

     /*   Log.d("KIKKEL", "this callback should be fired AFTER! ${NetworkSocket.socket?.connected()}")
        Log.d("KIKKEL", t.toString())
        Log.d("KIKKEL", "${t.uid}")
        Log.d("KIKKEL", t2.toString())
        Log.d("KIKKEL", "${t2.uid}")

        NetworkSocket.socket?.emit("new_action", t.toString())
*/
    }

}
