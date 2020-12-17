package com.example.itunessearch.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.itunessearch.R
import com.example.itunessearch.room.ResultRepository
import com.example.itunessearch.util.Resource
import com.example.itunessearch.vm.ResultVM
import com.example.itunessearch.vm.ResultVMProviderFactory
import kotlinx.android.synthetic.main.activity_main.*

class Dashboard : AppCompatActivity() {
    lateinit var viewModel: ResultVM

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)

        setContentView(R.layout.activity_main)
        val repository = ResultRepository()
        Log.d("Lj-final","one")

        val viewModelProviderFactory = ResultVMProviderFactory(repository,application)
        Log.d("Lj-final","two")

        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(ResultVM::class.java)

        bottomNavBar.setupWithNavController(fragment.findNavController())



    }



}