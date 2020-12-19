package com.example.itunessearch.frags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itunessearch.R
import com.example.itunessearch.adap.SearchAdapter
import com.example.itunessearch.app.Dashboard
import com.example.itunessearch.vm.ResultVM
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_saved.*


class Saved : Fragment() {
    lateinit var viewModel: ResultVM
    lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_saved, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = (activity as Dashboard).viewModel
        setupRecyclerView()
        viewModel.getSavedArtists().observe(viewLifecycleOwner, Observer {
            searchAdapter.differ.submitList(it)
        })

        searchAdapter.setOnItemClickListener {
            if (viewModel.hasInternetConnection()) {
                val bundle = Bundle().apply {
                    putSerializable("artist", it)
                }
                findNavController().navigate(R.id.action_saved_to_soloArtist, bundle)
            } else {
                Toast.makeText(activity,"No Internet Connection",Toast.LENGTH_SHORT).show()
            }
        }


        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val position = viewHolder.adapterPosition
                val artist = searchAdapter.differ.currentList[position]
                viewModel.deleteArtist(artist)
                Snackbar.make(view,"Article Deleted",Snackbar.LENGTH_LONG).apply {
                    setAction("Undo"){
                        viewModel.saveArtist(artist = artist)
                    }
                    show()
                }
            }

        }
        ItemTouchHelper(itemTouchHelperCallBack).apply {
            attachToRecyclerView(savedRV)
        }

    }

    private fun setupRecyclerView(){
        searchAdapter = SearchAdapter()
        savedRV.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}