package com.example.itunessearch.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.itunessearch.room.ResultRepository

class ResultVMProviderFactory
    (val resultRepository: ResultRepository,
     val app : Application

     ) : ViewModelProvider.Factory
    {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ResultVM(resultRepository,app) as T
        }



    }