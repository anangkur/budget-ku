package com.anangkur.budgetku.injection

import android.content.Context
import com.anangkur.budgetku.data.ArticleDataRepository
import com.anangkur.budgetku.data.AuthDataRepository
import com.anangkur.budgetku.data.BudgetDataRepository
import com.anangkur.budgetku.domain.impl.ArticlesSource
import com.anangkur.budgetku.domain.repository.BudgetRepository
import com.anangkur.budgetku.local.repository.AuthLocalRepository
import com.anangkur.budgetku.local.repository.BudgetLocalRepository
import com.anangkur.budgetku.local.repository.NewsLocalRepository
import com.anangkur.budgetku.remote.repository.AuthRemoteRepository
import com.anangkur.budgetku.remote.repository.BudgetRemoteRepository
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
    fun provideBudgetRepository(context: Context) = BudgetDataRepository.getInstance(
        BudgetLocalRepository.getInstance(context), BudgetRemoteRepository.getInstance()
    )
}