package com.example.handler

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast

class NewHandler(private val context: Context, looper: Looper = Looper.getMainLooper()): Handler(looper) {
    override fun handleMessage(msg: Message) {
        // Toast.makeText(context, "msg.what : ${msg.what}", Toast.LENGTH_SHORT).show()
        Log.d("NewHandler", "msg.what is : ${msg.what}")
    }
}