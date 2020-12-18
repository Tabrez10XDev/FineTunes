package com.example.itunessearch.room

import com.example.itunessearch.api.RetrofitInstance

class ResultRepository
 //   (
//    val db : ArticleDB
//    )
    {

        suspend fun getResults(artistID : String)=
            RetrofitInstance.api.getResults(artistID = artistID)

        suspend fun getArtists(term : String)=
            RetrofitInstance.api.getArtists(term = term)



    }