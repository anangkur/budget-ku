package com.anangkur.budgetku.remote.model.news

import com.google.gson.annotations.SerializedName

data class ArticleRemote(

    val id: Int = 0,

    @SerializedName("author")
    val author: String? = "",

    @SerializedName("title")
    val title: String? = "",

    @SerializedName("description")
    val description: String? = "",

    @SerializedName("url")
    val url: String? = "",

    @SerializedName("urlToImage")
    val urlToImage: String? = "",

    @SerializedName("publishedAt")
    val publishedAt: String? = "",

    @SerializedName("content")
    val content: String? = "",

    val category: String? = ""

)