package com.example.itunessearch.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database(
//    entities = [Article::class],
//    version = 2
//)
//abstract class ArticleDB : RoomDatabase() {
//
//    abstract fun getArticleDao() : ArticleDao
//
//    companion object{
//        @Volatile
//        private var instance: ArticleDB ?= null
//
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
//            instance?: createDB(context).also{
//                instance=it
//            }
//        }
//
//        private fun createDB(context: Context)=
//            Room.databaseBuilder(
//                context.applicationContext,
//                ArticleDB::class.java,
//                "article_db.db")
//                .build()
//    }
//}