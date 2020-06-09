package com.anangkur.budgetku.news.home

import com.anangkur.budgetku.model.ArticleIntent

interface HomeActionListener {
    fun onClickItem(data: ArticleIntent)
}