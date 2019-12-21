package com.itis.template.recycler

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class Song(val name: String,
                @RawRes val res: Int,
                @DrawableRes val cover: Int,
                val author: String,
                val album: String
)
