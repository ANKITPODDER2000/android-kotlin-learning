package com.example.composebasic.module

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
    @StringRes val titleId: Int,
    @DrawableRes val imageId: Int
)
