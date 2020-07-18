package com.anangkur.budgetku.presentation.mapper

import com.anangkur.budgetku.domain.model.news.Article
import com.anangkur.budgetku.presentation.model.news.ArticleView

class ArticleMapper: Mapper<ArticleView, Article> {

    companion object{
        private var INSTANCE: ArticleMapper? = null
        fun getInstance() = INSTANCE ?: ArticleMapper()
    }

    override fun mapToView(type: Article): ArticleView {
        return ArticleView(
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

    override fun mapFromView(type: ArticleView): Article {
        throw UnsupportedOperationException()
    }
}