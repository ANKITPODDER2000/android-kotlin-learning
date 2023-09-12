package com.example.postrequest.api

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name")
    val name: String,
    @SerializedName("job")
    val job: String,
)
