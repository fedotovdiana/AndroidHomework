package com.itis.template

data class Note(
        var id: Int,
        var title: String,
        var description: String,
        //     var date: Date,
        var longitude: Int,
        val latitude: Int
)