package com.anangkur.beritaku.news.home

import com.anangkur.beritaku.model.ArticleIntent

interface HomeActionListener {
    fun onClickItem(data: ArticleIntent)
}