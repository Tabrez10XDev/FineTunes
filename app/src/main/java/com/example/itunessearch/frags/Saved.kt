package com.example.itunessearch.frags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.itunessearch.R
import com.example.itunessearch.app.Dashboard
import com.example.itunessearch.vm.ResultVM


class Saved : Fragment() {
    lateinit var viewModel: ResultVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_saved, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = (activity as Dashboard).viewModel
    }
}