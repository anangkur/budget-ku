package com.anangkur.beritaku.data.repository

import com.anangkur.beritaku.data.model.ArticleEntity

interface ArticleLocal {
    suspend fun insertData(data: List<ArticleEntity>)
    suspend fun deleteByCategory(category: String)
    fun getAllDataByCategory(category: String): List<ArticleEntity>

    fun isExpired(): Boolean
}