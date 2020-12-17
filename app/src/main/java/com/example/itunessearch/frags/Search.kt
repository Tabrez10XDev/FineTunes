package com.example.itunessearch.frags

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itunessearch.R
import com.example.itunessearch.adap.SearchAdapter
import com.example.itunessearch.app.Dashboard
import com.example.itunessearch.util.Constants.Companion.SEARCH_ARTIST_TIME_DELAY
import com.example.itunessearch.util.Resource
import com.example.itunessearch.vm.ResultVM
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class Search : Fragment() {

    lateinit var viewModel: ResultVM
    lateinit var searchAdapter : SearchAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = (activity as Dashboard).viewModel

        var job : Job?= null

        setupRecyclerView()
        etSearch.addTextChangedListener{editable->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_ARTIST_TIME_DELAY)
                editable?.let {
                    if(editable.toString().isNotEmpty()){
                        viewModel.startFetchingArtists(editable.toString() )
                    }
                }
            }
        }

//        viewModel.RetrievedResults.observe(viewLifecycleOwner, Observer { response ->
//            when (response){
//                is Resource.Success ->{
//
//                    response.data?.let {Response ->
//
//                        Log.d("Lj-final",Response.toString())
//
//                    }
//                }
//                is Resource.Error -> {
//
//                    response.message?.let{
//                        Log.d("Lj-final", "Error: $it")
//                        Toast.makeText(activity,"Error $it", Toast.LENGTH_SHORT).show()
//                    }
//                }
//                is Resource.Loading->{
//                    Log.d("Lj-final","load")
//
//                }
//            }
//        })
        viewModel.RetrievedArtists.observe(viewLifecycleOwner, Observer { response ->
            when (response){
                is Resource.Success ->{

                    response.data?.let {Response ->

                        Log.d("Lj-final",Response.toString())
                        searchAdapter.differ.submitList(Response.results.toList())


                    }
                }
                is Resource.Error -> {

                    response.message?.let{
                        Log.d("Lj-final", "Error: $it")
                        Toast.makeText(activity,"Error $it", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading->{
                    Log.d("Lj-final","load")

                }
            }
        })

    }
    private fun setupRecyclerView(){
        searchAdapter = SearchAdapter()
        searchRV.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}