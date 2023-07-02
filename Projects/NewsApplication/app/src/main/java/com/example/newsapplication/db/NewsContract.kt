package com.example.newsapplication.db

import android.provider.BaseColumns

object NewsContract {
    const val DB_NAME = "news.db"
    const val DB_VERSION = 1

    object TopNews : BaseColumns {

        const val TABLE_NAME = "top_news_table"


        private const val ID = "_id"
        const val CATEGORY = "category"
        const val SOURCE_ID = "source_id"
        const val SOURCE_NAME = "source_name"
        const val AUTHOR = "author"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val URL = "url"
        const val URL_TO_IMAGE = "urlToImage"
        const val PUBLISHED_AT = "publishedAt"
        const val CONTENT = "content"
        private const val CREATED_AT = "created_at"

        const val CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                "$ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$CATEGORY TEXT, " +
                "$SOURCE_ID INTEGER, " +
                "$SOURCE_NAME TEXT, " +
                "$AUTHOR TEXT, " +
                "$TITLE TEXT, " +
                "$DESCRIPTION TEXT, " +
                "$URL TEXT, " +
                "$URL_TO_IMAGE TEXT, " +
                "$PUBLISHED_AT TEXT, " +
                "$CONTENT TEXT, " +
                "$CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP" +
            ")"
    }
}