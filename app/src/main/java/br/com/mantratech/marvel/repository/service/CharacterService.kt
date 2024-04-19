package br.com.mantratech.marvel.repository.service

import br.com.mantratech.marvel.model.CharacterDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("characters")
    fun getCharacters(
        @Query("ts") timestamp: Long,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ) : Call<CharacterDataModel>
}