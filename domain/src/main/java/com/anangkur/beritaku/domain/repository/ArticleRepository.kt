package com.anangkur.beritaku.domain.repository

import com.anangkur.beritaku.domain.model.Article
import com.anangkur.beritaku.domain.model.BaseResult

interface ArticleRepository {
    suspend fun clearArticle(category: String)
    suspend fun saveArticles(articles: List<Article>)
    fun getArticlesLocal(category: String): List<Article>
    suspend fun getArticleRemote(
        apiKey: String?,
        country: String?,
        category: String?,
        sources: String?,
        q: String?
    ): BaseResult<List<Article>>
}