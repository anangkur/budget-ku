package com.anangkur.beritaku.data.repository

import com.anangkur.beritaku.data.model.ArticleEntity
import com.anangkur.beritaku.data.model.BaseResultEntity

interface ArticleRemote {
    suspend fun getTopHeadlinesNews(
        apiKey: String?,
        country: String?,
        category: String?,
        sources: String?,
        q: String?
    ): BaseResultEntity<List<ArticleEntity>>
}