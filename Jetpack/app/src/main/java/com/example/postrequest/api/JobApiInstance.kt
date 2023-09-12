package com.example.postrequest.api

import android.annotation.SuppressLint
import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException


object JobApiInstance {
    private const val baseUrl = "https://reqres.in/api/"
    val jobApi: JobApi = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .apply {
            getUnsafeOkHttpClient()?.let {
                client(it.build())
            }
        }
        .build()
        .create(JobApi::class.java)


    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder? {
        return try {
            val trustAllCerts = arrayOf<TrustManager>(
                @SuppressLint("CustomX509TrustManager")
                object : X509TrustManager {
                    @SuppressLint("TrustAllX509TrustManager")
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate>,
                        authType: String,
                    ) {
                    }

                    @SuppressLint("TrustAllX509TrustManager")
                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate>,
                        authType: String,
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf()
                    }
                }
            )

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(HostnameVerifier { _, _ -> true })
            builder.addInterceptor(Interceptor {
                // response.body.string()
                val response = it.proceed(it.request().newBuilder().build())
                Log.d("DEBUG_ANKIT", "getUnsafeOkHttpClient: request is : ${response.request.toString()}")
                Log.d("DEBUG_ANKIT", "getUnsafeOkHttpClient: $response")
                return@Interceptor response
            })
            /*builder.addNetworkInterceptor(Interceptor {
                it.proceed(it.request())
            })*/
            builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}