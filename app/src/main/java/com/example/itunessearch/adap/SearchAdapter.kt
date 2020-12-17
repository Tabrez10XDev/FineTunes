package com.example.itunessearch.adap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.itunessearch.R
import com.example.itunessearch.data.Artist
import kotlinx.android.synthetic.main.artists_list.view.*

class SearchAdapter:RecyclerView.Adapter<SearchAdapter.ResultViewHolder>() {
    inner class ResultViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Artist>(){
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem.artistId == newItem.artistId
        }

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.artists_list,parent,false ))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val artist = differ.currentList[position]
        holder.itemView.apply {
            artist_name.text = artist.artistName
            artist_type.text = artist.artistType
            primary_genre.text = artist.primaryGenreName
            setOnClickListener {
                onItemClickListener?.let { it(artist) }
            }
        }


    }

    private var onItemClickListener : ((Artist) -> Unit)?= null

    fun setOnItemClickListener(listener:(Artist)->Unit){
        onItemClickListener = listener
    }
}