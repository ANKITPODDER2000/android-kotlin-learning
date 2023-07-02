package com.example.newsapplication.constants

class UtilityConstants {
    companion object {
        const val API_KEY = "108272c9a3eb450f813dc4303975e772"
        const val BASE_URL = "https://newsapi.org/v2/"
        const val MAIN_TAG = "DEBUG_ANKIT"

        const val NEWS_DOWNLOADED = 0

        val NEWS_CATEGORY = arrayListOf(
            "business",
            "entertainment",
            "general",
            "health",
            "science",
            "sports",
            "technology"
        )
    }
}