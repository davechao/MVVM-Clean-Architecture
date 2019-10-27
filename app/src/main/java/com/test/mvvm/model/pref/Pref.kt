package com.test.mvvm.model.pref

import com.squareup.moshi.Moshi
import com.test.mvvm.model.pref.vo.TokenData

class Pref(private val moshi: Moshi, preferenceFileName: String) :
    AbstractPref(preferenceFileName) {

    private val tokenPref = StringPref("TOKEN")

    var token: TokenData
        get() =
            try {
                val jsonAdapter = moshi.adapter(TokenData::class.java)
                jsonAdapter.fromJson(tokenPref.get().toString())!!
            } catch (e: Exception) {
                TokenData()
            }
        set(value) {
            val jsonAdapter = moshi.adapter(TokenData::class.java)
            tokenPref.set(jsonAdapter.toJson(value))
        }

    fun clearToken() {
        tokenPref.remove()
    }
}