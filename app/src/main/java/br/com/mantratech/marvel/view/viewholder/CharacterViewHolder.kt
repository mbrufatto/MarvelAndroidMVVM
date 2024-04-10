package br.com.mantratech.marvel.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import br.com.mantratech.marvel.R
import br.com.mantratech.marvel.databinding.CharacterviewBinding
import br.com.mantratech.marvel.model.CharacterModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CharacterViewHolder(private val itemBinding: CharacterviewBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bindData(character: CharacterModel) {
        itemBinding.characterName.text = character.name

        val imageUrl = "${character.thumbnail.path}.${character.thumbnail.extension}"

        Glide.with(itemBinding.root)
            .load(imageUrl)
            .apply(RequestOptions()
                .placeholder(R.mipmap.ic_image_placeholder)
                .error(R.mipmap.ic_image_error)
            )
            .into(itemBinding.characterImage)
    }
}