package com.example.newsapplication

import android.os.AsyncTask
import android.util.Log
import java.lang.Exception
import java.net.URL
import org.json.JSONObject
import java.net.HttpURLConnection

private const val TAG = "DownloadData"

class DownloadData : AsyncTask<String, Void, String>() {
    lateinit var shareData: (ArrayList<News>) -> Unit
    override fun doInBackground(vararg params: String): String {
        var result = ""
        Log.d(TAG, "In DownloadData")


        val url = URL(params[0])
        val connection = url.openConnection() as HttpURLConnection

        try {
            connection.requestMethod = "GET"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Accept", "application/json")
            connection.setRequestProperty("User-Agent", "My App User Agent")

            // Read the response body
            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = connection.inputStream
                result = inputStream.bufferedReader().use { it.readText() }
            } else {
                val errorStream = connection.errorStream
                val errorMessage = errorStream.bufferedReader().use { it.readText() }
            }
        } catch (e: Exception) {
        } finally {
            connection.disconnect()
        }

        return result
    }

    override fun onPostExecute(result: String) {
        var allNews = ArrayList<News>()
        val obj = JSONObject(result)
        val articlesArray = obj.getJSONArray("articles")
        for (i in 0 until articlesArray.length()) {
            val article = articlesArray.getJSONObject(i)
            allNews.add(
                News(
                    article.getString("author"),
                    article.getString("title"),
                    article.getString("description"),
                    article.getString("url"),
                    article.getString("urlToImage"),
                    article.getString("publishedAt"),
                    article.getString("content")
                )
            )
        }
        shareData(allNews)
    }

    fun setGetDataMethod( shareData : (ArrayList<News>) -> Unit) {
        this.shareData = shareData
    }
}