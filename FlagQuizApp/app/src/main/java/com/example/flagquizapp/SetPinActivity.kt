package com.example.flagquizapp

import android.app.KeyguardManager
import android.app.admin.DevicePolicyManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.flagquizapp.util.Security


private const val SET_PIN = 1
private const val VERIFY_PIN = 2
class SetPinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DEBUG_ANKIT", "In OnCreate Method, value of isUnlock is : ${Security.isUnlock} : ${this.localClassName}")
    }

    override fun onResume() {
        super.onResume()
        Log.d("DEBUG_ANKIT", "OnResume is Called... : ${this.localClassName}")

        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        if(!Security.isUnlock && keyguardManager.isKeyguardSecure && keyguardManager.isDeviceSecure) {
            Log.d("DEBUG_ANKIT_PIN", "Verify Auth")
            keyguardManager.createConfirmDeviceCredentialIntent(
                "Unlock the App",
                "To Use this application please authenticate yourself"
            ).also {
                startActivityForResult(it, VERIFY_PIN)
            }
        }
        else if(!Security.isUnlock) {
            Log.d("DEBUG_ANKIT_PIN", "Set PIN")
            Intent(DevicePolicyManager.ACTION_SET_NEW_PASSWORD).also {
                startActivityForResult(it, SET_PIN)
            }
        }
        else{
            Log.d("DEBUG_ANKIT_PIN", "EVERYTHING is DONE")
            Intent().also {
                setResult(RESULT_OK, it)
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK) {
            when(requestCode) {
                SET_PIN -> {
                    Log.d("DEBUG_ANKIT_PIN", "Callback for SET_PIN : ${this.localClassName}")
                }
                VERIFY_PIN -> {
                    Log.d("DEBUG_ANKIT_PIN", "Callback for VERIFY_PIN : ${this.localClassName}")
                    Security.setIsUnlock(true)
                }
            }

            Intent().also {
                setResult(RESULT_OK, it)
            }
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("DEBUG_ANKIT", "OnStart method is Called : ${this.localClassName}")

    }

    override fun onPause() {
        super.onPause()
        Log.d("DEBUG_ANKIT", "onPause method is Called : ${this.localClassName}")
    }

    override fun onStop() {
        super.onStop()
        Log.d("DEBUG_ANKIT", "onStop method is Called : ${this.localClassName}")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("DEBUG_ANKIT", "onRestart method is Called : ${this.localClassName}")
    }
}