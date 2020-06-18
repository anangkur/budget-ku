package com.anangkur.budgetku.remote.repository

import com.anangkur.budgetku.data.model.ArticleEntity
import com.anangkur.budgetku.data.model.BaseResultEntity
import com.anangkur.budgetku.data.repository.news.ArticleRemote
import com.anangkur.budgetku.remote.ApiService
import com.anangkur.budgetku.remote.BaseDataSource
import com.anangkur.budgetku.remote.mapper.ArticleMapper
import com.anangkur.budgetku.remote.mapper.BaseResultMapper
import com.anangkur.budgetku.remote.model.base.BaseResultModel

class NewsRemoteRepository (
    private val mapper: ArticleMapper,
    private val baseResultMapper: BaseResultMapper<List<ArticleEntity>>,
    private val service: ApiService
): ArticleRemote, BaseDataSource() {

    companion object{
        private var INSTANCE: NewsRemoteRepository? = null
        fun getInstance() = INSTANCE
            ?: NewsRemoteRepository(
                ArticleMapper.getInstance(),
                BaseResultMapper.getInstance(),
                ApiService.getApiService
            )
    }

    override suspend fun getTopHeadlinesNews(
        apiKey: String?,
        country: String?,
        category: String?,
        sources: String?,
        q: String?
    ): BaseResultEntity<List<ArticleEntity>> {
        val response = service.getTopHeadlinesNews(
            apiKey,
            country,
            category,
            sources,
            q
        )
        return if (response.status == "ok"){
            val data = response.articles.map { mapper.mapFromRemote(it) }
            baseResultMapper.mapFromRemote(BaseResultModel.success(data))
        }else{
            baseResultMapper.mapFromRemote(BaseResultModel.error(response.message?:""))
        }
    }
}