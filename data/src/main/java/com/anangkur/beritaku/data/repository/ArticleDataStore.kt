package com.anangkur.beritaku.data.repository

import com.anangkur.beritaku.data.model.ArticleEntity
import com.anangkur.beritaku.data.model.BaseResultEntity

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