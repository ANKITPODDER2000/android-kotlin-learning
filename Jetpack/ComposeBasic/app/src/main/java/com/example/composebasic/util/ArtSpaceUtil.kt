package com.example.composebasic.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.composebasic.R

class ArtSpaceUtil {
    data class ArtSpace(
        @DrawableRes val imgResId: Int,
        @StringRes val titleResId: Int,
        @StringRes val authorResId: Int,
    )

    companion object {
        val allArtSpaces = arrayListOf(
            ArtSpace(
                R.drawable.eiffel_tower, R.string.eiffel_tower,
                R.string.eiffel_tower_author
            ),
            ArtSpace(
                R.drawable.clock_tower, R.string.clock_tower,
                R.string.clock_tower
            )
        )
    }
}