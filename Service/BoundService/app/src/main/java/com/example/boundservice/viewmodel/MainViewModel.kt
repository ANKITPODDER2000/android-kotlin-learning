package com.example.boundservice.viewmodel

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.boundservice.service.RandomNumberService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "DEBUG_ANKIT"

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    var mRandomNumberService: RandomNumberService? = null
        private set
    var mRandomServiceBind = false
        private set
    private lateinit var mContext: Context

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d(TAG, "onServiceConnected: is called")
            mRandomServiceBind = true
            mRandomNumberService = (service as RandomNumberService.RandomNumberBinder).getService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(TAG, "onServiceDisconnected: is called")
            mRandomServiceBind = true
            mRandomNumberService = null
        }

    }
    fun setContext(context: Context) {
        mContext = context
    }
    fun startBindService() {
        Log.d(TAG, "startBindService: is called")
        Intent(mContext, RandomNumberService::class.java).also {
            mContext.bindService(it, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    fun stopBindService() {
        Log.d(TAG, "stopBindService: is called")
        if(!mRandomServiceBind) return
        Intent(mContext, RandomNumberService::class.java).also {
            mContext.unbindService(serviceConnection)
        }
        mRandomServiceBind = false
        mRandomNumberService = null
    }

    fun printRandomNum() {
        CoroutineScope(Dispatchers.IO).launch {
            if(mRandomServiceBind) {
                Log.d(TAG, "printRandomNum: random number is : ${mRandomNumberService?.getRandomNumber()}")
            }
            delay(2000)
            printRandomNum()
        }
    }

}