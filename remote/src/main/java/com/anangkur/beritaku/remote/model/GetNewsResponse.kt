package com.anangkur.beritaku.remote.model

import com.google.gson.annotations.SerializedName

data class GetNewsResponse(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("totalResults")
    val totalResults: Int = 0,
    @SerializedName("articles")
    val articles: List<ArticleModel> = listOf(),
    @SerializedName("message")
    val message: String? = ""
)