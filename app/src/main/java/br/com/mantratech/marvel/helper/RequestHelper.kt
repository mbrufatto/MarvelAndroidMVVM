package br.com.mantratech.marvel.helper

import br.com.mantratech.marvel.BuildConfig

class RequestHelper private constructor() {

    companion object {
        fun createParams() : RequestHelper {
            val generator = RequestHelper()
            generator.setParams()
            return generator
        }
    }

    val publicKey: String = BuildConfig.PUBLIC_KEY
    val privateKey: String = BuildConfig.PRIVATE_KEY
    var timeStamp: Long? = null
    var hash: String? = null

    private fun setParams() {
        timeStamp = System.currentTimeMillis()
        hash = "$timeStamp$privateKey$publicKey".toMD5()
    }
}
