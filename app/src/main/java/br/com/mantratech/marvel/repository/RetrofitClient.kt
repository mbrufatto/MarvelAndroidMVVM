package br.com.mantratech.marvel.repository

import br.com.mantratech.marvel.service.constants.MarvelConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp
import java.util.Date

class RetrofitClient private constructor() {

    companion object {

        private lateinit var INSTANCE: Retrofit
        private var apiKey: String = ""
        private var ts: String = ""
        private var hash: String = ""

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request()
                        .newBuilder()
                        .build()
                    return chain.proceed(request)
                }
            })

            if (!::INSTANCE.isInitialized) {
                synchronized(RetrofitClient::class) {
                    INSTANCE = Retrofit.Builder()
                        .baseUrl("https://gateway.marvel.com:443/v1/public/")
                        .client(httpClient.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
            return INSTANCE
        }

        fun <T> getService(serviceClass: Class<T>): T {
            return getRetrofitInstance().create(serviceClass)
        }
    }
}