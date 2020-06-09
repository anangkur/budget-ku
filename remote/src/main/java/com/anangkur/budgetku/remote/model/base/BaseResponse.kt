package com.anangkur.budgetku.remote.model.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("data")
    val data: T,
    @SerializedName("message")
    val message: String? = ""
)