package com.apollo.base.features.authentication

import com.apollo.base.data.models.User


interface BaseAuthenticator {

    fun login(user: String, password: String, onSuccess: ((User) -> Unit)?, onFailure: (() -> Unit)?)

    fun isLogged(): Boolean

    fun logout()

}