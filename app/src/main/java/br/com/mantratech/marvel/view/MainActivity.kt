package br.com.mantratech.marvel.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import br.com.mantratech.marvel.databinding.ActivityMainBinding
import br.com.mantratech.marvel.view.adapter.CharactersAdapter
import br.com.mantratech.marvel.viewmodel.CharacterViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CharacterViewModel
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val adapter = CharactersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.characters.layoutManager = GridLayoutManager(applicationContext, 2)
        binding.characters.adapter = adapter

        viewModel.getCharacters()

        observe()
    }

    private fun observe() {
        viewModel.characters.observe(this) {
            adapter.updateCharacters(it)
        }
    }
}