package com.anangkur.budgetku.data.repository.news

import com.anangkur.budgetku.data.model.news.ArticleEntity
import com.anangkur.budgetku.data.model.BaseResultEntity

interface ArticleDataStore {
    suspend fun insertData(data: List<ArticleEntity>)
    suspend fun deleteByCategory(category: String)
    fun getAllDataByCategory(category: String): List<ArticleEntity>
    suspend fun getTopHeadlinesNews(
        apiKey: String?,
        country: String?,
        category: String?,
        sources: String?,
        q: String?
    ): BaseResultEntity<List<ArticleEntity>>
}