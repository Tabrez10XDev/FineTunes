package com.example.itunessearch.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.itunessearch.data.Artist

@Database(
    entities = [Artist::class],
    version = 3
)
abstract class ArtistDB : RoomDatabase() {

    abstract fun getArtistDao() : ArtistDao

    companion object{
        @Volatile
        private var instance: ArtistDB ?= null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?: createDB(context).also{
                instance=it
            }
        }

        private fun createDB(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                ArtistDB::class.java,
                "artist_db.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}