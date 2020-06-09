package com.anangkur.budgetku.injection

import android.content.Context
import com.anangkur.budgetku.data.ArticleDataRepository
import com.anangkur.budgetku.domain.GetArticles
import com.anangkur.budgetku.local.LocalRepository
import com.anangkur.budgetku.remote.RemoteRepository

object Injection{
    fun provideGetArticle(context: Context) = GetArticles.getInstance(
        ArticleDataRepository.getInstance(
            LocalRepository.getInstance(context), RemoteRepository.getInstance()
        )
    )
}