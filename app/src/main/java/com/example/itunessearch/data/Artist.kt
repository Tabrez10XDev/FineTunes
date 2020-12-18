package com.example.itunessearch.data

import java.io.Serializable


data class Artist(
    val amgArtistId: Int,
    val artistId: Int,
    val artistLinkUrl: String,
    val artistName: String,
    val artistType: String,
    val primaryGenreId: Int,
    val primaryGenreName: String,
    val wrapperType: String
) : Serializable