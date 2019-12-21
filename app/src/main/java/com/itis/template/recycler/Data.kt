package com.itis.template.recycler

import com.itis.template.R

object Data {

    val songs: ArrayList<Song> = arrayListOf(
            Song("Yesterday", R.raw.yesterday, R.drawable.y, "The Beatles", "Help!"),
            Song("Moon River", R.raw.moon_river, R.drawable.mm, "Perry Como", ""),
            Song("Let it Snow!", R.raw.song1, R.drawable.fs, "Frank Sinatra", "Let it snow"),
            Song("Ring those Christmas Bells", R.raw.song2, R.drawable.pl, "Peggy Lee", "Christmas"),
            Song("Fever", R.raw.song1, R.drawable.pl, "Peggy Lee", ""),
            Song("Help!", R.raw.song2, R.drawable.y, "The Beatles", "Help!"),
            Song("New York, New York", R.raw.song1, R.drawable.fs, "Frank Sinatra", "Nothing but the best"),
            Song("Oh, Pretty Woman", R.raw.song2, R.drawable.pw, "Ray Orbison", ""),
            Song("Strangers in the night", R.raw.song1, R.drawable.fs, "Frank Sinatra", "Strangers in the night")
    )
}
