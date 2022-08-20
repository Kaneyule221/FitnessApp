package com.example.repfluentv2.util

import com.example.repfluentv2.Models.AuthToken

class SessionManage () {

//    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    fun saveAuthToken(token: String) {
        AuthToken.instance?.setAuth(token)
    }

    fun fetchAuthToken(): String? {
        return AuthToken.instance?.AuthToken
    }
}