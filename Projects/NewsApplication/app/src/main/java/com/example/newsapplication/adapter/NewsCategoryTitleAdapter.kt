package com.example.newsapplication.adapter

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.newsapplication.databinding.NewsCategoryTitleBinding
import com.example.newsapplication.model.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL


class NewsCategoryTitleAdapter(private val newsList: List<News>) :
    Adapter<NewsCategoryTitleAdapter.NewsCategoryTitleViewHolder>() {
    inner class NewsCategoryTitleViewHolder(val binding: NewsCategoryTitleBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsCategoryTitleViewHolder {
        val binding =
            NewsCategoryTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsCategoryTitleViewHolder(binding)
    }

    override fun getItemCount() = newsList.size

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: NewsCategoryTitleViewHolder, position: Int) {
        holder.binding.tvNewsCategoryTitle.text = newsList[position].title
        /*if (newsList[position].urlToImage != null && newsList[position].urlToImage?.length!! > 0)
            Glide.with(context)
                .load(newsList[position].urlToImage)
                .into(holder.binding.ivNewsCategory)
        else
            holder.binding.ivNewsCategory.setImageDrawable(context.getDrawable(R.drawable.img_not_found))*/
        val imgUrl = newsList[position].urlToImage
        if (!imgUrl.isNullOrEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val img = drawableFromUrl(imgUrl)
                    holder.binding.ivNewsCategory.setImageDrawable(img)
                }
                catch (_: Exception) {}
            }
        }
    }

    private fun drawableFromUrl(url: String?): Drawable {
        val x: Bitmap
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.connect()
        val input = connection.inputStream
        x = BitmapFactory.decodeStream(input)
        return BitmapDrawable(Resources.getSystem(), x)
    }
}