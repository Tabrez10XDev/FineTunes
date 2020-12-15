package com.example.itunessearch.data

import com.example.itunessearch.room.Article

data class Result(
    val resultCount: Int,
    val results: MutableList<Article>
)