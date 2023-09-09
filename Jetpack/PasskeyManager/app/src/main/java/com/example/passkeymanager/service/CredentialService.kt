package com.example.passkeymanager.service

import android.app.PendingIntent
import android.content.Intent
import android.os.CancellationSignal
import android.os.OutcomeReceiver
import android.util.Log
import androidx.credentials.exceptions.ClearCredentialException
import androidx.credentials.exceptions.CreateCredentialException
import androidx.credentials.exceptions.CreateCredentialUnknownException
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.provider.BeginCreateCredentialRequest
import androidx.credentials.provider.BeginCreateCredentialResponse
import androidx.credentials.provider.BeginCreatePublicKeyCredentialRequest
import androidx.credentials.provider.BeginGetCredentialRequest
import androidx.credentials.provider.BeginGetCredentialResponse
import androidx.credentials.provider.CreateEntry
import androidx.credentials.provider.CredentialProviderService
import androidx.credentials.provider.ProviderClearCredentialStateRequest
import kotlin.random.Random

private const val TAG = "DEBUG_ANKIT"

class CredentialService : CredentialProviderService() {
    override fun onBeginCreateCredentialRequest(
        request: BeginCreateCredentialRequest,
        cancellationSignal: CancellationSignal,
        callback: OutcomeReceiver<BeginCreateCredentialResponse, CreateCredentialException>,
    ) {
        Log.d(TAG, "onBeginCreateCredentialRequest: is called for request : $request")
        when (request) {
            is BeginCreatePublicKeyCredentialRequest -> {}
            else -> {
                callback.onError(CreateCredentialUnknownException())
                return
            }
        }
        val response  = createResponse(request)
        callback.onResult(response)
    }

    private fun createResponse(
        request: BeginCreatePublicKeyCredentialRequest,
    ): BeginCreateCredentialResponse {

        // Adding two create entries - one for storing credentials to the 'Personal'
        // account, and one for storing them to the 'Family' account. These
        // accounts are local to this sample app only.
        val createEntries: MutableList<CreateEntry> = mutableListOf()
        createEntries.add(
            CreateEntry(
                "abc",
                createNewPendingIntent("PERSONAL_ACCOUNT_ID", "com.example.passkeymanager.create_pass_intent")
            )
        )
        createEntries.add(
            CreateEntry(
                "family",
                createNewPendingIntent("PERSONAL_ACCOUNT_ID", "com.example.passkeymanager.create_pass_intent")
            )
        )


        return BeginCreateCredentialResponse(createEntries)
    }

    private fun createNewPendingIntent(accountId: String, action: String): PendingIntent {
        val intent = Intent(action).setPackage("com.example.passkeymanager")
        intent.putExtra("EXTRA_KEY_ACCOUNT_ID", accountId)

        return PendingIntent.getActivity(
            applicationContext, Random.nextInt(10000,50000),
            intent, (
                    PendingIntent.FLAG_MUTABLE
                            or PendingIntent.FLAG_UPDATE_CURRENT
                    )
        )
    }

    override fun onBeginGetCredentialRequest(
        request: BeginGetCredentialRequest,
        cancellationSignal: CancellationSignal,
        callback: OutcomeReceiver<BeginGetCredentialResponse, GetCredentialException>,
    ) {
        Log.d(TAG, "onBeginGetCredentialRequest: is called")
    }

    override fun onClearCredentialStateRequest(
        request: ProviderClearCredentialStateRequest,
        cancellationSignal: CancellationSignal,
        callback: OutcomeReceiver<Void?, ClearCredentialException>,
    ) {
        Log.d(TAG, "onClearCredentialStateRequest: is called")
    }
}