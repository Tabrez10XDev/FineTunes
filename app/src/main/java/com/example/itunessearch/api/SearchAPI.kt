package com.example.itunessearch.api

import com.example.itunessearch.data.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPI {
    @GET("search")
    suspend fun getResults(
        @Query("term")
        term: String,

        @Query("media")
        media: String = "music"

    ): Response<Result>
}