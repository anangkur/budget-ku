package com.anangkur.budgetku.injection

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anangkur.budgetku.domain.impl.ArticlesSource
import com.anangkur.budgetku.domain.model.Article
import com.anangkur.budgetku.domain.repository.AuthRepository
import com.anangkur.budgetku.presentation.features.app.SplashViewModel
import com.anangkur.budgetku.presentation.features.auth.*
import com.anangkur.budgetku.presentation.features.budget.DetailProjectViewModel
import com.anangkur.budgetku.presentation.features.budget.DetailSpendViewModel
import com.anangkur.budgetku.presentation.features.dashboard.HomeViewModel
import com.anangkur.budgetku.presentation.features.news.NewsViewModel
import com.anangkur.budgetku.presentation.mapper.ArticleMapper
import com.anangkur.budgetku.presentation.mapper.BaseResultMapper

class ViewModelFactory(
    private val authRepository: AuthRepository,
    private val articlesSource: ArticlesSource,
    private val articleMapper: ArticleMapper,
    private val baseResultMapper: BaseResultMapper<List<Article>>
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T  =
        with(modelClass) {
            when {
                isAssignableFrom(NewsViewModel::class.java) -> NewsViewModel(articlesSource, articleMapper, baseResultMapper)

                isAssignableFrom(SplashViewModel::class.java) -> SplashViewModel(authRepository)

                isAssignableFrom(EditPasswordViewModel::class.java) -> EditPasswordViewModel(authRepository)
                isAssignableFrom(EditProfileViewModel::class.java) -> EditProfileViewModel(authRepository)
                isAssignableFrom(ForgotPasswordViewModel::class.java) -> ForgotPasswordViewModel(authRepository)
                isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(authRepository)
                isAssignableFrom(SignInViewModel::class.java) -> SignInViewModel(authRepository)
                isAssignableFrom(SignUpViewModel::class.java) -> SignUpViewModel(authRepository)

                isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel()

                isAssignableFrom(DetailProjectViewModel::class.java) -> DetailProjectViewModel()
                isAssignableFrom(DetailSpendViewModel::class.java) -> DetailSpendViewModel()
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object{
        @Volatile private var INSTANCE: ViewModelFactory? = null
        fun getInstance(context: Context) = INSTANCE ?: synchronized(ViewModelFactory::class.java){
            INSTANCE ?: ViewModelFactory(
                Injection.provideAuthRepository(context),
                Injection.provideNewsSource(context),
                ArticleMapper.getInstance(),
                BaseResultMapper.getInstance()
            ).also { INSTANCE = it }
        }
    }
}