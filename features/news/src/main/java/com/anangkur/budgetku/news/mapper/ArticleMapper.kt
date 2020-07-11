package com.anangkur.budgetku.news.mapper

import com.anangkur.budgetku.mapper.Mapper
import com.anangkur.budgetku.news.model.ArticleIntent
import com.anangkur.budgetku.presentation.model.news.ArticleView

class ArticleMapper:
    Mapper<ArticleIntent, ArticleView> {
    override fun mapToIntent(type: ArticleView): ArticleIntent {
        return ArticleIntent(
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

    override fun mapFromIntent(type: ArticleIntent): ArticleView {
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
}