package com.itis.template.advert_page

import com.itis.template.R

object DataAdverts {
    var adverts = mutableListOf(
            Advert(
                    "New Film",
                    R.drawable.img4,
                    "Angelina Jolie stared in Maleficent",
                    listOf(R.drawable.cinema, R.drawable.window, R.drawable.kino)
            ),
            Advert(
                    "Attention",
                    R.drawable.img4,
                    "Today is a good day for watching new films at our cinema!",
                    listOf(R.drawable.window, R.drawable.cinema, R.drawable.kino)
            ),
            Advert(
                    "New Film",
                    R.drawable.img4,
                    "Angelina Jolie stared in Maleficent",
                    listOf(R.drawable.cinema, R.drawable.window, R.drawable.kino)
            )
    )
}
