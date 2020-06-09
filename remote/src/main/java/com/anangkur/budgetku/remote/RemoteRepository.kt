package com.anangkur.budgetku.remote

import com.anangkur.budgetku.data.model.ArticleEntity
import com.anangkur.budgetku.data.model.BaseResultEntity
import com.anangkur.budgetku.data.repository.ArticleRemote
import com.anangkur.budgetku.remote.mapper.ArticleMapper
import com.anangkur.budgetku.remote.mapper.BaseResultMapper
import com.anangkur.budgetku.remote.model.BaseResultModel

class RemoteRepository (
    private val mapper: ArticleMapper,
    private val baseResultMapper: BaseResultMapper<List<ArticleEntity>>,
    private val service: ApiService
): ArticleRemote, BaseDataSource() {

    companion object{
        private var INSTANCE: RemoteRepository? = null
        fun getInstance() = INSTANCE ?: RemoteRepository(
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