package com.anangkur.budgetku.data.mapper.news

import com.anangkur.budgetku.data.mapper.Mapper
import com.anangkur.budgetku.data.model.news.ArticleEntity
import com.anangkur.budgetku.domain.model.news.Article

class ArticleMapper:
    Mapper<ArticleEntity, Article> {

    companion object{
        private var INSTANCE: ArticleMapper? = null
        fun getInstance() = INSTANCE
            ?: ArticleMapper()
    }

    override fun mapToEntity(type: Article): ArticleEntity {
        return ArticleEntity(
            id = type.id,
            urlToImage = type.urlToImage,
            url = type.url,
            publishedAt = type.publishedAt,
            description = type.description,
            content = type.content,
            category = type.category,
            author = type.author,
            title = type.title
        )
    }

    override fun mapFromEntity(type: ArticleEntity): Article {
        return Article(
            id = type.id,
            urlToImage = type.urlToImage,
            url = type.url,
            publishedAt = type.publishedAt,
            description = type.description,
            content = type.content,
            category = type.category,
            author = type.author,
            title = type.title
        )
    }
}