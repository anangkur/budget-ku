package com.anangkur.budgetku.data.source.news

import com.anangkur.budgetku.data.repository.news.ArticleDataStore
import com.anangkur.budgetku.data.repository.news.ArticleLocal
import com.anangkur.budgetku.data.repository.news.ArticleRemote

class ArticleDataStoreFactory (
    private val articleLocalDataStore: ArticleLocalDataStore,
    private val articleRemoteDataStore: ArticleRemoteDataStore
) {

    companion object{
        private var INSTANCE: ArticleDataStoreFactory? = null
        fun getInstance(
            articleLocal: ArticleLocal,
            articleRemote: ArticleRemote
        ) = INSTANCE
            ?: ArticleDataStoreFactory(
                ArticleLocalDataStore.getInstance(
                    articleLocal
                ),
                ArticleRemoteDataStore.getInstance(
                    articleRemote
                )
            )
    }

    fun retrieveCacheDataStore(): ArticleDataStore {
        return articleLocalDataStore
    }

    fun retrieveRemoteDataStore(): ArticleDataStore {
        return articleRemoteDataStore
    }
}