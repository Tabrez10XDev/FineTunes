package com.example.itunessearch.frags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.itunessearch.R
import com.example.itunessearch.app.Dashboard
import com.example.itunessearch.vm.ResultVM
import kotlinx.android.synthetic.main.fragment_solo_artist.*


class SoloArtist : Fragment() {



    lateinit var viewModel: ResultVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_solo_artist, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = (activity as Dashboard).viewModel
        var args : SoloArtistArgs = SoloArtistArgs.fromBundle(this.requireArguments())

        var artist = args.artist
        webView.apply {
            webViewClient = WebViewClient()

            loadUrl(artist.artistLinkUrl)
        }


        fab.setOnClickListener{
            viewModel.saveArtist(artist)
            Toast.makeText(activity,"Artist Saved",Toast.LENGTH_SHORT).show()
        }


    }
}