package com.anangkur.budgetku.data.source.auth

import com.anangkur.budgetku.data.repository.auth.AuthDataSource
import com.anangkur.budgetku.data.repository.auth.AuthLocal
import com.anangkur.budgetku.data.repository.auth.AuthRemote

class AuthDataStoreFactory(
    private val authRemoteDataStore: AuthRemoteDataStore,
    private val authLocalDataStore: AuthLocalDataStore
) {
    companion object{
        private var INSTANCE: AuthDataStoreFactory? = null
        fun getInstance(
            authRemote: AuthRemote,
            authLocal: AuthLocal
        ) = INSTANCE
            ?: AuthDataStoreFactory(
                AuthRemoteDataStore.getInstance(authRemote),
                AuthLocalDataStore.getInstance(authLocal)
            )
    }

    fun retrieveRemoteDataStore(): AuthDataSource {
        return authRemoteDataStore
    }

    fun retrieveLocalDataStore(): AuthDataSource {
        return authLocalDataStore
    }
}