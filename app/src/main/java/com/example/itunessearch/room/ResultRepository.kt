package com.example.itunessearch.room

import com.example.itunessearch.api.RetrofitInstance

class ResultRepository
//    (
//    val db : ArtistDB
//    )
    {


        suspend fun getArtists(term : String)=
            RetrofitInstance.api.getArtists(term = term)

//        suspend fun upsert(artist: Artist) = db.getArtistDao().upsert(artist)
//
//        fun getSavedNews() = db.getArtistDao().getAllArticles()

    }