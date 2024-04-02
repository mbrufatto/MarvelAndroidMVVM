package br.com.mantratech.marvel.repository

import android.content.Context
import br.com.mantratech.marvel.R
import br.com.mantratech.marvel.helper.RequestHelper
import br.com.mantratech.marvel.model.CharacterDataModel
import br.com.mantratech.marvel.repository.service.CharacterService
import br.com.mantratech.marvel.service.listener.APIListener

class CharacterRepository(context: Context) : BaseRepository(context) {
    private val remote = RetrofitClient.getService(CharacterService::class.java)

    fun getCharacters(listener: APIListener<CharacterDataModel>) {
        if(!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
        }

        val generator = RequestHelper.createParams()
        executeCall(remote.getCharacters(generator.timeStamp, generator.publicKey, generator.hash), listener)
    }
}