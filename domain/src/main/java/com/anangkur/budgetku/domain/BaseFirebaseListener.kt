package com.anangkur.budgetku.domain

interface BaseFirebaseListener<T> {
    fun onLoading(isLoading: Boolean)
    fun onSuccess(data: T)
    fun onFailed(errorMessage: String)
}