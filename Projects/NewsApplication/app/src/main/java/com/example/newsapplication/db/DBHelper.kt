package com.example.newsapplication.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.newsapplication.db.NewsContract.TopNews
import com.example.newsapplication.model.News
import com.example.newsapplication.model.Source

class DBHelper private constructor() {

    private lateinit var mDBOpenHelper: DBOpenHelper
    private lateinit var mDB: SQLiteDatabase

    private constructor(context: Context) : this() {
        mDBOpenHelper = DBOpenHelper(context, NewsContract.DB_NAME, NewsContract.DB_VERSION)
        mDB = mDBOpenHelper.writableDatabase
    }

    companion object {
        private var sInstance: DBHelper? = null
        fun getInstance(context: Context): DBHelper {
            if (sInstance == null) {
                sInstance = DBHelper(context)
            }
            return sInstance!!
        }

        fun getNewsContentValues(title: String, news: News): ContentValues {
            val contentValues = ContentValues()
            contentValues.put(TopNews.CATEGORY, title)
            contentValues.put(TopNews.SOURCE_ID, news.source.id)
            contentValues.put(TopNews.SOURCE_NAME, news.source.name)
            contentValues.put(TopNews.AUTHOR, news.author)
            contentValues.put(TopNews.TITLE, news.title)
            contentValues.put(TopNews.DESCRIPTION, news.description)
            contentValues.put(TopNews.URL, news.url)
            contentValues.put(TopNews.URL_TO_IMAGE, news.urlToImage)
            contentValues.put(TopNews.PUBLISHED_AT, news.publishedAt)
            contentValues.put(TopNews.CONTENT, news.content)
            return contentValues
        }

        @SuppressLint("Range")
        fun getNews(cursor: Cursor): News {
            return News(
                Source(
                    cursor.getString(cursor.getColumnIndex(TopNews.SOURCE_ID)),
                    cursor.getString(cursor.getColumnIndex(TopNews.SOURCE_NAME))
                ),
                cursor.getString(cursor.getColumnIndex(TopNews.AUTHOR)),
                cursor.getString(cursor.getColumnIndex(TopNews.TITLE)),
                cursor.getString(cursor.getColumnIndex(TopNews.DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(TopNews.URL)),
                cursor.getString(cursor.getColumnIndex(TopNews.URL_TO_IMAGE)),
                cursor.getString(cursor.getColumnIndex(TopNews.PUBLISHED_AT)),
                cursor.getString(cursor.getColumnIndex(TopNews.CONTENT))
            )
        }
    }

    fun insertNews(values: ContentValues): Long {
        mDB.beginTransaction()
        val rowId = mDB.insert(TopNews.TABLE_NAME, null, values)
        mDB.setTransactionSuccessful()
        mDB.endTransaction()
        return rowId
    }

    fun deleteNews(category: String): Int {
        mDB.beginTransaction()
        val isDeleted = mDB.delete(TopNews.TABLE_NAME, "${TopNews.CATEGORY} = ?", arrayOf(category))
        mDB.setTransactionSuccessful()
        mDB.endTransaction()

        return isDeleted
    }

    fun queryNews(selection: String, selectionArgs: ArrayList<String>): Cursor? {
        return mDB.query(
            TopNews.TABLE_NAME,
            null,
            selection,
            selectionArgs.toTypedArray(),
            null,
            null,
            null,
            null
        )
    }

}