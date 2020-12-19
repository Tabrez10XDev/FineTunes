package com.example.itunessearch.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "Artists"
)
data class Artist(
    @PrimaryKey(autoGenerate = true)
    val amgArtistId: Int,
    val artistId: Int,
    val artistLinkUrl: String,
    val artistName: String,
    val artistType: String,
    val primaryGenreId: Int,
    val primaryGenreName: String ?= "None" ,
    val wrapperType: String
) : Serializable