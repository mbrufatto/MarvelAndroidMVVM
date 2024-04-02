package br.com.mantratech.marvel.service.listener

interface APIListener<T> {
    fun onSuccess(result: T)
    fun onFailure(message: String)
}