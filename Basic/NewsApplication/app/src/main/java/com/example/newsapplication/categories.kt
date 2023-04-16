package com.example.newsapplication

private const val API_KEY = "108272c9a3eb450f813dc4303975e772"
private const val country = "in"

data class Categories(
    var categoryName: String,
    var urlPath: String = "https://newsapi.org/v2/top-headlines?country=${country}&category=${categoryName}&apiKey=${API_KEY}",
)
var allCategories = arrayListOf<Categories>(
    Categories("all", "https://newsapi.org/v2/top-headlines?country=${country}&apiKey=${API_KEY}"),
    Categories("business"),
    Categories("entertainment"),
    Categories("general"),
    Categories("health"),
    Categories("science"),
    Categories("sports"),
    Categories("technology")
)