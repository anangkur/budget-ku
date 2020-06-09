package com.anangkur.budgetku.domain.impl

import com.anangkur.budgetku.domain.repository.AuthRepository

open class AuthSource(private val authRepository: AuthRepository) {

    companion object{
        private var INSTANCE: AuthSource? = null
        fun getInstance(authRepository: AuthRepository) = INSTANCE
            ?: AuthSource(authRepository)
    }


}