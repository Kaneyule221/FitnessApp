package com.example.repfluentv2.Models

import userinfo.Userinformation

class AuthToken {
    var AuthToken: String = ""


    constructor() {}

    constructor(
        AuthToken: String,
    ) {
        this.AuthToken = AuthToken

    }

    fun setAuth(AuthToken1:String) {
        AuthToken = AuthToken1
    }

    fun getAuthToken1(): String {
        return AuthToken
    }

    companion object {
        var instance: AuthToken? = null
            get() {
                if (field == null) field = AuthToken()
                return field
            }
    }

    override fun toString(): String {
        return "Token: $AuthToken"

    }
}