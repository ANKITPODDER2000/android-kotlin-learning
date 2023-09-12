package com.example.passkeymanager

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.credentials.CreatePublicKeyCredentialRequest
import androidx.credentials.CredentialManager
import com.example.passkeymanager.ui.theme.PasskeyManagerTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "DEBUG_ANKIT"
class MainActivity : ComponentActivity() {
    private lateinit var credentialManager: CredentialManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        credentialManager = CredentialManager.create(this)
        setContent {
            PasskeyManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android", credentialManager)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, credentialManager: CredentialManager, modifier: Modifier = Modifier) {
    val context = LocalContext.current as Context
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = { handleButtonClick(credentialManager, context) }) {
            Text(text = "Save Data")
        }
    }
}

fun handleButtonClick(credentialManager: CredentialManager, context: Context) {
    val req = CreatePublicKeyCredentialRequest(requestJson = "{\n" +
            "  \"challenge\": \"nhkQXfE59Jb97VyyNJkvDiXucMEvltduvcrDmGrODHY\",\n" +
            "  \"rp\": {\n" +
            "    \"name\": \"CredMan App Test\",\n" +
            "    \"id\": \"credential-manager-app-test.glitch.me\"\n" +
            "  },\n" +
            "  \"user\": {\n" +
            "    \"id\": \"2HzoHm_hY0CjuEESY9tY6-3SdjmNHOoNqaPDcZGzsr0\",\n" +
            "    \"name\": \"helloandroid@gmail.com\",\n" +
            "    \"displayName\": \"helloandroid@gmail.com\"\n" +
            "  },\n" +
            "  \"pubKeyCredParams\": [\n" +
            "    {\n" +
            "      \"type\": \"public-key\",\n" +
            "      \"alg\": -7\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"public-key\",\n" +
            "      \"alg\": -257\n" +
            "    }\n" +
            "  ],\n" +
            "  \"timeout\": 1800000,\n" +
            "  \"attestation\": \"none\",\n" +
            "  \"excludeCredentials\": [],\n" +
            "  \"authenticatorSelection\": {\n" +
            "    \"authenticatorAttachment\": \"platform\",\n" +
            "    \"requireResidentKey\": true,\n" +
            "    \"residentKey\": \"required\",\n" +
            "    \"userVerification\": \"required\"\n" +
            "  }\n" +
            "}")
    CoroutineScope(Dispatchers.IO).launch {
        try {
            credentialManager.createCredential(
                context,
                req
            )
        } catch (e: Exception) {
            Log.d(TAG, "handleButtonClick: error is called")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PasskeyManagerTheme {

    }
}