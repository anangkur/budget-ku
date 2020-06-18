package com.anangkur.budgetku.injection

import android.content.Context
import com.anangkur.budgetku.data.ArticleDataRepository
import com.anangkur.budgetku.data.AuthDataRepository
import com.anangkur.budgetku.domain.impl.ArticlesSource
import com.anangkur.budgetku.local.repository.AuthLocalRepository
import com.anangkur.budgetku.local.repository.NewsLocalRepository
import com.anangkur.budgetku.remote.repository.AuthRemoteRepository
import com.anangkur.budgetku.remote.repository.NewsRemoteRepository

object Injection{
    fun provideNewsSource(context: Context) = ArticlesSource.getInstance(
        ArticleDataRepository.getInstance(
            NewsLocalRepository.getInstance(context), NewsRemoteRepository.getInstance()
        )
    )
    fun provideAuthRepository(context: Context) = AuthDataRepository.getInstance(
        AuthLocalRepository.getInstance(context), AuthRemoteRepository.getInstance()
    )
}