package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val thread1 = MyThread1()
        Log.d(TAG, "Going to Start the thread.....")
        thread1.start()
        Log.d(TAG, "After starting the thread.....")
        Thread {
            whileLoop@while (true) {
                try {
                    thread1.handler.post {
                        Log.d(TAG, Thread.currentThread().name)
                        Log.d(TAG, "Ping the handler...")
                    }
                    break@whileLoop
                } catch (e: Exception) {
                    Thread.sleep(100)
                }
            }
        }.start()


        Log.d(TAG, "======================================")

        val thread2 = MyThread2()
        thread2.start()
        Thread {
            while (!thread2.isThreadReady) {}
            thread2.handler.toString().let { Log.d(TAG, it) }
            thread2.handler.post {
                Log.d(TAG, "Call from MyThread2...")
            }
        }.start()
    }
}