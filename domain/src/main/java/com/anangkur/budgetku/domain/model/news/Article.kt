package com.anangkur.budgetku.domain.model.news

data class Article(
    val id: Int = 0,
    val author: String? = "",
    val title: String? = "",
    val description: String? = "",
    val url: String? = "",
    val urlToImage: String? = "",
    val publishedAt: String? = "",
    val content: String? = "",
    var category: String? = ""
)