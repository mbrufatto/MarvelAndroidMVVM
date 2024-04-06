package br.com.mantratech.marvel.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.mantratech.marvel.model.CharacterDataModel
import br.com.mantratech.marvel.model.CharacterModel
import br.com.mantratech.marvel.repository.CharacterRepository
import br.com.mantratech.marvel.service.listener.APIListener

class CharacterViewModel(application: Application) : AndroidViewModel(application) {

    private val characterRepository = CharacterRepository(application.applicationContext)

    private val _characters = MutableLiveData<List<CharacterModel>>()
    val characters: LiveData<List<CharacterModel>> = _characters

    fun getCharacters() {
        val listener = object : APIListener<CharacterDataModel> {
            override fun onSuccess(result: CharacterDataModel) {
                _characters.value = result.data.results!!
            }

            override fun onFailure(message: String) {
                println(message)
            }
        }

        characterRepository.getCharacters(listener)
    }
}