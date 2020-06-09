package com.anangkur.beritaku.data

import com.anangkur.beritaku.data.mapper.ArticleMapper
import com.anangkur.beritaku.data.repository.ArticleLocal
import com.anangkur.beritaku.data.repository.ArticleRemote
import com.anangkur.beritaku.data.source.ArticleDataStoreFactory
import com.anangkur.beritaku.domain.model.Article
import com.anangkur.beritaku.domain.model.BaseResult
import com.anangkur.beritaku.domain.repository.ArticleRepository

class ArticleDataRepository (
    private val factory: ArticleDataStoreFactory,
    private val mapper: ArticleMapper
): ArticleRepository {

    companion object{
        private var INSTANCE: ArticleDataRepository? = null
        fun getInstance(
            articleLocal: ArticleLocal,
            articleRemote: ArticleRemote
        ) = INSTANCE ?: ArticleDataRepository(
            ArticleDataStoreFactory.getInstance(articleLocal, articleRemote),
            ArticleMapper.getInstance()
        )
    }

    override suspend fun clearArticle(category: String) {
        factory.retrieveCacheDataStore().deleteByCategory(category)
    }

    override suspend fun saveArticles(articles: List<Article>) {
        factory.retrieveCacheDataStore().insertData(articles.map { mapper.mapToEntity(it) })
    }

    override fun getArticlesLocal(category: String): List<Article> {
        return factory.retrieveCacheDataStore().getAllDataByCategory(category).map { mapper.mapFromEntity(it) }
    }

    override suspend fun getArticleRemote(
        apiKey: String?,
        country: String?,
        category: String?,
        sources: String?,
        q: String?
    ): BaseResult<List<Article>> {
        val result = factory.retrieveRemoteDataStore().getTopHeadlinesNews(apiKey, country, category, sources, q)
        return BaseResult(
            result.status,
            result.data?.map { mapper.mapFromEntity(it) },
            result.message,
            result.isLoading
        )
    }

}