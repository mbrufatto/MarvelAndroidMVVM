package br.com.mantratech.marvel.model

import com.google.gson.annotations.SerializedName

data class CharacterDataModel(
    @SerializedName("data")
    val data: CharactersModel
)


data class CharactersModel(
    @SerializedName("results")
    val results: List<CharacterModel>?
)

class CharacterModel {
    @SerializedName("name")
    var name: String = ""

    @SerializedName("description")
    var description: String = ""

    @SerializedName("thumbnail")
    var thumbnail: Thumbnail = Thumbnail()
}

class Thumbnail {
    @SerializedName("path")
    var path: String = ""

    @SerializedName("extension")
    var extension: String = ""
}