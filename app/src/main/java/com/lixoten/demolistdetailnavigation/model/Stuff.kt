package com.lixoten.demolistdetailnavigation.model

import androidx.annotation.DrawableRes

data class Stuff(
    val title: String = "",
    @DrawableRes val imageResId: Int,
    val shortDescription: String = "",
    val longDescription: String = "",
)
