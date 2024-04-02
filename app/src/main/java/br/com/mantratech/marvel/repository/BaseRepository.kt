package br.com.mantratech.marvel.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import br.com.mantratech.marvel.R
import br.com.mantratech.marvel.model.CharacterDataModel
import br.com.mantratech.marvel.service.constants.MarvelConstants
import br.com.mantratech.marvel.service.listener.APIListener
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseRepository(val context: Context) {
    fun failResponse(str: String): String {
        return Gson().fromJson(str, String::class.java)
    }

    fun <T> executeCall(call: Call<T>, listener: APIListener<T>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if(response.code() == MarvelConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess(it) }
                } else {
                    listener.onFailure(failResponse(response.errorBody()!!.string()))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

    fun isConnectionAvailable() : Boolean {
        var result = false

        val connection = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNet = connection.activeNetwork ?: return false
            val networkCapabilities = connection.getNetworkCapabilities(activeNet) ?: return false

            result = when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            if (connection.activeNetworkInfo != null) {
                result = when (connection.activeNetworkInfo!!.type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return result
    }
}