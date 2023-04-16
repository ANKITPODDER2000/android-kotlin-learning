package com.example.flagquizapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.PictureDrawable
import android.util.AttributeSet
import com.caverock.androidsvg.SVG
import okhttp3.*
import java.io.IOException


class SvgImageView(context: Context, attrs: AttributeSet?) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {
    fun setImageSvgUrl(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle error
            }

            override fun onResponse(call: Call, response: Response) {
                val inputStream = response.body()?.byteStream()
                val svg = SVG.getFromInputStream(inputStream)

                val picture = svg.renderToPicture()
                val drawable = PictureDrawable(picture)
                val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitmap)
                canvas.drawPicture(picture)

                post {
                    setImageBitmap(bitmap)
                }
            }
        })
    }
}
