package com.anangkur.budgetku.data.source.news

import com.anangkur.budgetku.data.model.news.ArticleEntity
import com.anangkur.budgetku.data.model.BaseResultEntity
import com.anangkur.budgetku.data.repository.news.ArticleDataStore
import com.anangkur.budgetku.data.repository.news.ArticleRemote

class ArticleRemoteDataStore (private val articleRemote: ArticleRemote):
    ArticleDataStore {

    companion object{
        private var INSTANCE: ArticleRemoteDataStore? = null
        fun getInstance(articleRemote: ArticleRemote) = INSTANCE
            ?: ArticleRemoteDataStore(
                articleRemote
            )
    }

    override suspend fun insertData(data: List<ArticleEntity>) {
        throw UnsupportedOperationException()
    }

    override suspend fun deleteByCategory(category: String) {
        throw UnsupportedOperationException()
    }

    override fun getAllDataByCategory(category: String): List<ArticleEntity> {
        throw UnsupportedOperationException()
    }

    override suspend fun getTopHeadlinesNews(
        apiKey: String?,
        country: String?,
        category: String?,
        sources: String?,
        q: String?
    ): BaseResultEntity<List<ArticleEntity>> {
        return articleRemote.getTopHeadlinesNews(apiKey, country, category, sources, q)
    }
}