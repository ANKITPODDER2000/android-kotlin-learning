package com.example.authenticationusingbiometricprompt

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val promptInfo = BiometricPrompt.PromptInfo.Builder().setTitle("Verify yourself")
            .setDescription("User authentication is required.")
            .setAllowedAuthenticators(DEVICE_CREDENTIAL)
            .build()
        val executor = ContextCompat.getMainExecutor(this)

        val authenticationCallback: BiometricPrompt.AuthenticationCallback =
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    Log.d("DUBUG_ANKIT", "Method name : onAuthenticationError")
                    finish()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    Log.d("DUBUG_ANKIT", "Method name : onAuthenticationSucceeded")
                }

                override fun onAuthenticationFailed() {
                    Log.d("DUBUG_ANKIT", "Method name : onAuthenticationFailed")
                    finish()
                }
            }

        val biometricPrompt = BiometricPrompt(this, executor, authenticationCallback)
        biometricPrompt.authenticate(promptInfo)
    }
}