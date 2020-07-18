package com.anangkur.budgetku.injection

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anangkur.budgetku.domain.impl.ArticlesSource
import com.anangkur.budgetku.domain.repository.AuthRepository
import com.anangkur.budgetku.domain.repository.BudgetRepository
import com.anangkur.budgetku.presentation.features.app.SplashViewModel
import com.anangkur.budgetku.presentation.features.auth.*
import com.anangkur.budgetku.presentation.features.budget.AddProjectViewModel
import com.anangkur.budgetku.presentation.features.budget.DetailProjectViewModel
import com.anangkur.budgetku.presentation.features.budget.DetailSpendViewModel
import com.anangkur.budgetku.presentation.features.budget.SelectCategoryViewModel
import com.anangkur.budgetku.presentation.features.dashboard.HomeViewModel
import com.anangkur.budgetku.presentation.features.news.NewsViewModel

class ViewModelFactory(
    private val authRepository: AuthRepository,
    private val budgetRepository: BudgetRepository,
    private val articlesSource: ArticlesSource
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T  =
        with(modelClass) {
            when {
                isAssignableFrom(NewsViewModel::class.java) -> NewsViewModel(articlesSource)

                isAssignableFrom(SplashViewModel::class.java) -> SplashViewModel(authRepository)

                isAssignableFrom(EditPasswordViewModel::class.java) -> EditPasswordViewModel(authRepository)
                isAssignableFrom(EditProfileViewModel::class.java) -> EditProfileViewModel(authRepository)
                isAssignableFrom(ForgotPasswordViewModel::class.java) -> ForgotPasswordViewModel(authRepository)
                isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(authRepository)
                isAssignableFrom(SignInViewModel::class.java) -> SignInViewModel(authRepository)
                isAssignableFrom(SignUpViewModel::class.java) -> SignUpViewModel(authRepository)

                isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(budgetRepository, authRepository)

                isAssignableFrom(DetailProjectViewModel::class.java) -> DetailProjectViewModel()
                isAssignableFrom(DetailSpendViewModel::class.java) -> DetailSpendViewModel()
                isAssignableFrom(SelectCategoryViewModel::class.java) -> SelectCategoryViewModel(budgetRepository)
                isAssignableFrom(AddProjectViewModel::class.java) -> AddProjectViewModel(budgetRepository)

                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object{
        @Volatile private var INSTANCE: ViewModelFactory? = null
        fun getInstance(context: Context) = INSTANCE ?: synchronized(ViewModelFactory::class.java){
            INSTANCE ?: ViewModelFactory(
                Injection.provideAuthRepository(context),
                Injection.provideBudgetRepository(context),
                Injection.provideNewsSource(context)
            ).also { INSTANCE = it }
        }
    }
}