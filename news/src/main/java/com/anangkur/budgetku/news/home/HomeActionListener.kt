package com.anangkur.budgetku.news.home

import com.anangkur.budgetku.news.model.ArticleIntent

interface HomeActionListener {
    fun onClickItem(data: ArticleIntent)
}