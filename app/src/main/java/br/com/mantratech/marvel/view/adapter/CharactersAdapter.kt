package br.com.mantratech.marvel.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mantratech.marvel.R
import br.com.mantratech.marvel.databinding.CharacterviewBinding
import br.com.mantratech.marvel.model.CharacterModel
import br.com.mantratech.marvel.view.viewholder.CharacterViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifBitmapProvider
import com.bumptech.glide.request.RequestOptions

class CharactersAdapter : RecyclerView.Adapter<CharacterViewHolder>() {

    private var listCharacter: List<CharacterModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val itemBinding = CharacterviewBinding.inflate(inflater, parent, false)
        return CharacterViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bindData(listCharacter[position])
    }

    override fun getItemCount(): Int {
        return listCharacter.count()
    }

    fun updateCharacters(list: List<CharacterModel>) {
        listCharacter = list
        notifyDataSetChanged()
    }
}