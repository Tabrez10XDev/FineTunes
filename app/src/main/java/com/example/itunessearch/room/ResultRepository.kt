package com.example.itunessearch.room

import com.example.itunessearch.api.RetrofitInstance
import com.example.itunessearch.data.Artist

class ResultRepository
    (
    val db : ArtistDB
    )
    {


        suspend fun getArtists(term : String)=
            RetrofitInstance.api.getArtists(term = term)

        suspend fun upsert(artist: Artist) = db.getArtistDao().upsert(artist)

        fun getSavedArtists() = db.getArtistDao().getAllArtists()

        suspend fun deleteArtist(artist: Artist) = db.getArtistDao().deleteArticles(artist)


    }