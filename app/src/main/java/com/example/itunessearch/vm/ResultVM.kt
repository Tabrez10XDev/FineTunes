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
import com.example.itunessearch.data.Result
import com.example.itunessearch.room.ResultRepository
import com.example.itunessearch.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class ResultVM
    (val resultRepository : ResultRepository,
     app : Application

     ) : AndroidViewModel(app)
{

    init {
        Log.d("Lj-final","four")

        startFetchingResults("Taylor Swift")

    }

    var RetrievedResponse : Result ?= null
    val RetrievedResults : MutableLiveData<Resource<Result>> = MutableLiveData()

    private suspend fun getResults(term: String){
        Log.d("Lj-final","pre")

//        RetrievedResults.postValue(Resource.Loading())
        Log.d("Lj-final","past")

        try {
            if(hasInternetConnection()){
                Log.d("Lj-final","if")

                val response = resultRepository.getResults(term)
                RetrievedResults.postValue(handleResponse(response))
                Log.d("Lj-final","if")

            }
            else{
                Log.d("Lj-final","else")

                RetrievedResults.postValue(Resource.Error("No Internet Connection"))
            }
        }catch(e : Exception){
            Log.d("Lj-final",e.message)

            RetrievedResults.postValue(Resource.Error("Catchable"))
        }
    }

    fun startFetchingResults(term : String) = viewModelScope.launch {
        Log.d("Lj-final","three")

        getResults(term)

    }


    private fun handleResponse(response: Response<Result>) : Resource<Result> {
        if(response.isSuccessful){
            response.body()?.let {
                    RetrievedResponse = it


                return Resource.Success(RetrievedResponse ?: it)
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