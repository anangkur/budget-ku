package com.anangkur.budgetku.data.repository.auth

interface AuthLocal {
    fun saveFirebaseToken(firebaseToken: String)
    fun loadFirebaseToken(): String
}