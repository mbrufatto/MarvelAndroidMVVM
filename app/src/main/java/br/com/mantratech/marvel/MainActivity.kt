package br.com.mantratech.marvel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.mantratech.marvel.databinding.ActivityMainBinding
import br.com.mantratech.marvel.viewmodel.CharacterViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)

        viewModel.getCharacters()

        observe()
    }

    private fun observe() {
        viewModel.characters.observe(this) {
            println(it?.get(0)?.name)
        }
    }
}