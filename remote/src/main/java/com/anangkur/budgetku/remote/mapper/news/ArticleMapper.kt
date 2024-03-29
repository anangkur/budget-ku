package com.anangkur.budgetku.remote.mapper.news

import com.anangkur.budgetku.data.model.news.ArticleEntity
import com.anangkur.budgetku.remote.mapper.Mapper
import com.anangkur.budgetku.remote.model.news.ArticleRemote
import java.lang.UnsupportedOperationException

class ArticleMapper:
    Mapper<ArticleRemote, ArticleEntity> {

    companion object{
        private var INSTANCE: ArticleMapper? = null
        fun getInstance() = INSTANCE
            ?: ArticleMapper()
    }

    override fun mapFromRemote(type: ArticleRemote): ArticleEntity {
        return ArticleEntity(
            id = type.id,
            title = type.title,
            author = type.author,
            category = type.category,
            content = type.content,
            description = type.description,
            publishedAt = type.publishedAt,
            url = type.url,
            urlToImage = type.urlToImage
        )
    }

    override fun mapToRemote(type: ArticleEntity): ArticleRemote {
        throw UnsupportedOperationException()
    }
}