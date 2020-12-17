package com.example.itunessearch.room

import com.example.itunessearch.api.RetrofitInstance

class ResultRepository
 //   (
//    val db : ArticleDB
//    )
    {

        suspend fun getResults(term : String)=
            RetrofitInstance.api.getResults(term = term)

        suspend fun getArtists(term : String)=
            RetrofitInstance.api.getArtists(term = term)



    }