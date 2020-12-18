package com.example.itunessearch.room

import androidx.lifecycle.LiveData
import androidx.room.*

//@Dao
//interface ArtistDao {
//
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun upsert(artist: Artist):Long
//
//    @Query("SELECT * FROM artists")
//    fun getAllArticles():LiveData<List<Artist>>
//
//    @Delete
//    suspend fun deleteArticles(article: Artist)
//
//}