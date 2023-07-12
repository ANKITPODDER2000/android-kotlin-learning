package com.example.flagquizapp

import android.app.KeyguardManager
import android.app.admin.DevicePolicyManager
import android.content.Context
import android.content.Intent
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_WEAK
import android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import com.example.flagquizapp.util.Security
import java.util.concurrent.Executor
import java.util.concurrent.Executors


private const val SET_PIN = 1
private const val VERIFY_PIN = 2
class SetPinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onResume() {
        super.onResume()
        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        if(!Security.isUnlock && keyguardManager.isKeyguardSecure && keyguardManager.isDeviceSecure) {
//            Log.d("DEBUG_ANKIT_PIN", "Verify Auth")
//            keyguardManager.createConfirmDeviceCredentialIntent(
//                "Unlock the App",
//                "To Use this application please authenticate yourself"
//            ).also {
//                keyguardManager.setAllowedAuthenticators(KeyguardManager.Authenticators.DEVICE_CREDENTIAL)
//                startActivityForResult(it, VERIFY_PIN)
//            }
            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Title")
                .setSubtitle("Subtitle")
                .setDescription("Description")
                .setDeviceCredentialAllowed(true)
                .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_WEAK)
                .build()
            val executor: Executor = Executors.newSingleThreadExecutor()
            val biometricPrompt = BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    // User authentication succeeded
                }

                override fun onAuthenticationFailed() {
                    // User authentication failed
                }
            })

            biometricPrompt.authenticate(promptInfo)

        }
        else if(!Security.isUnlock) {
            Intent(DevicePolicyManager.ACTION_SET_NEW_PASSWORD).also {
                startActivityForResult(it, SET_PIN)
            }
        }
        else{
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
        else {
            Intent().also {
                setResult(RESULT_CANCELED, it)
            }
            finish()
        }
    }
}