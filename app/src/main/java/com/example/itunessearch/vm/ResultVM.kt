package com.example.itunessearch.vm

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.itunessearch.app.DashboardApplication
import com.example.itunessearch.data.ArtistsResults
import com.example.itunessearch.room.ResultRepository
import com.example.itunessearch.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class ResultVM
    (val resultRepository : ResultRepository,
     app : Application

     ) : AndroidViewModel(app)
{




    var RetrievedArtists : MutableLiveData<Resource<ArtistsResults>> = MutableLiveData()
    var RetrievedArtistResponse : ArtistsResults ?= null


    private suspend fun getArtists(term: String){

        RetrievedArtists.postValue(Resource.Loading())
        try {

            if(hasInternetConnection()){
                val response = resultRepository.getArtists(term)
                RetrievedArtists.postValue(handleArtistsResponse(response))
            }
            else{
                RetrievedArtists.postValue(Resource.Error("No Internet Connection"))
            }
        }catch(e : Exception){
            RetrievedArtists.postValue(Resource.Error("Catchable"))
        }
    }



    fun startFetchingArtists(term : String) = viewModelScope.launch {
        getArtists(term)
    }




    private fun handleArtistsResponse(response: Response<ArtistsResults>) : Resource<ArtistsResults> {
        if(response.isSuccessful){
            response.body()?.let {
                RetrievedArtistResponse = it


                return Resource.Success(RetrievedArtistResponse ?: it)
            }
        }
        return Resource.Error(response.message())

    }


    private fun hasInternetConnection(): Boolean{
        Log.d("Lj-final","Last but")
        val connectivityManager = getApplication<DashboardApplication>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        Log.d("Lj-final","Lastttt")

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetworks = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetworks) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR)-> true
                capabilities.hasTransport(TRANSPORT_ETHERNET)->true
                else -> false
            }
        }
        else{
            connectivityManager.activeNetworkInfo?.run {
                return when(type){
                    TYPE_WIFI-> true
                    TYPE_MOBILE->true
                    TYPE_ETHERNET->true
                    else -> false
                }

            }
        }
        return false
    }

}