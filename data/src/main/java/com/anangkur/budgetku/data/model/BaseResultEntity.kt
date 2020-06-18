package com.anangkur.budgetku.data.model

data class BaseResultEntity<out T>(
    val status: Int,
    val data: T?,
    val message: String?,
    val isLoading: Boolean?
)