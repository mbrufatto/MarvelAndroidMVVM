package br.com.mantratech.marvel.service.constants

class MarvelConstants private constructor() {

    // Requisições API
    object HEADER {
        const val API_KEY = "apikey"
        const val TS = "ts"
        const val HASH = "hash"
    }

    object HTTP {
        const val SUCCESS = 200
    }
}