package com.anangkur.beritaku.data.mapper

import com.anangkur.beritaku.data.model.ArticleEntity
import com.anangkur.beritaku.domain.model.Article

class ArticleMapper: Mapper<ArticleEntity, Article> {

    companion object{
        private var INSTANCE: ArticleMapper? = null
        fun getInstance() = INSTANCE ?:ArticleMapper()
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