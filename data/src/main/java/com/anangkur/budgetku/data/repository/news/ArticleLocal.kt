package com.anangkur.budgetku.data.repository.news

import com.anangkur.budgetku.data.model.ArticleEntity

interface ArticleLocal {
    suspend fun insertData(data: List<ArticleEntity>)
    suspend fun deleteByCategory(category: String)
    fun getAllDataByCategory(category: String): List<ArticleEntity>

    fun isExpired(): Boolean
}