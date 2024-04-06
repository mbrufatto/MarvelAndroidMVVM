package br.com.mantratech.marvel.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import br.com.mantratech.marvel.databinding.CharacterviewBinding
import br.com.mantratech.marvel.model.CharacterModel

class CharacterViewHolder(private val itemBinding: CharacterviewBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bindData(character: CharacterModel) {
        itemBinding.characterName.text = character.name
    }
}