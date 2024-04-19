package br.com.mantratech.marvel.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.mantratech.marvel.model.CharacterDataModel
import br.com.mantratech.marvel.model.CharacterModel
import br.com.mantratech.marvel.repository.CharacterRepository
import br.com.mantratech.marvel.service.listener.APIListener

class CharacterViewModel(application: Application) : AndroidViewModel(application) {

    private val characterRepository = CharacterRepository(application.applicationContext)
    private var spanCount = 2
    private var currentOffset = 0

    private lateinit var recyclerView: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager

    private val _characters = MutableLiveData<List<CharacterModel>?>()
    val characters: LiveData<List<CharacterModel>?> = _characters

    fun setRecycleView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        gridLayoutManager = GridLayoutManager(recyclerView.context, spanCount)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.addOnScrollListener(scrollListener)
    }

    fun getCharacters(offset: Int = 0, limit: Int = 20) {
        val listener = object : APIListener<CharacterDataModel> {
            override fun onSuccess(result: CharacterDataModel) {
                val currentList = characters.value ?: emptyList()
                val newList = if (currentList.isEmpty()) {
                    result.data.results
                } else {
                    result.data.results?.let { currentList.plus(it.toList()) }
                }
                _characters.value = newList as List<CharacterModel>?
            }
            override fun onFailure(message: String) {
                println(message)
            }
        }
        characterRepository.getCharacters(listener, offset = currentOffset, limit = 20)
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            if (dy <= 0) { return }

            val manager = recyclerView.layoutManager as GridLayoutManager
            val totalItem = manager.itemCount

            val last = manager.findLastCompletelyVisibleItemPosition()
            if (last - 5 == totalItem - 6) {
                currentOffset += 20
                getCharacters(currentOffset)
            }

        }
    }
}