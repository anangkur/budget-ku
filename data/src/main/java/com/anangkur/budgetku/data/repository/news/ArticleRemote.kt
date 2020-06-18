package com.anangkur.budgetku.data.repository.news

import com.anangkur.budgetku.data.model.ArticleEntity
import com.anangkur.budgetku.data.model.BaseResultEntity

interface ArticleRemote {
    suspend fun getTopHeadlinesNews(
        apiKey: String?,
        country: String?,
        category: String?,
        sources: String?,
        q: String?
    ): BaseResultEntity<List<ArticleEntity>>
}