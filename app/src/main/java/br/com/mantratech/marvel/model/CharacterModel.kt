package br.com.mantratech.marvel.model

import com.google.gson.annotations.SerializedName

data class CharacterDataModel(
    @SerializedName("data")
    val data: CharactersModel
)

class CharactersModel {

    @SerializedName("offset")
    var offset: Int = 0

    @SerializedName("limit")
    var limit: Int = 0

    @SerializedName("total")
    var total: Int = 0

    @SerializedName("count")
    var count: Int = 0

    @SerializedName("results")
    val results: List<CharacterModel>? = null
}

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