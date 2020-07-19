package com.anangkur.budgetku.presentation.features.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.model.budget.Project
import com.anangkur.budgetku.domain.repository.AuthRepository
import com.anangkur.budgetku.domain.repository.BudgetRepository
import com.anangkur.budgetku.presentation.mapper.budget.ProjectMapper
import com.anangkur.budgetku.presentation.model.auth.UserView
import com.anangkur.budgetku.presentation.model.dashboard.ProjectView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val budgetRepository: BudgetRepository,
    private val authRepository: AuthRepository
): ViewModel() {

    private val projectMapper = ProjectMapper.getInstance()

    val loadingGetProject = MutableLiveData<Boolean>()
    val successGetProject = MutableLiveData<List<ProjectView>>()
    val errorGetProject = MutableLiveData<String>()
    fun getProject() {
        CoroutineScope(Dispatchers.IO).launch {
            budgetRepository.getProject(object : BaseFirebaseListener<List<Project>> {
                override fun onLoading(isLoading: Boolean) {
                    loadingGetProject.postValue(isLoading)
                }
                override fun onSuccess(data: List<Project>) {
                    successGetProject.postValue(data.map { projectMapper.mapToView(it) })
                }
                override fun onFailed(errorMessage: String) {
                    errorGetProject.postValue(errorMessage)
                }
            })
        }
    }

    private val userInternalSetter = MutableLiveData<UserView>()
    val userPublicObserver: LiveData<UserView> = userInternalSetter
    fun createDummyUser() {
        val user = UserView(
            userId = "0",
            name = "Anang Kur",
            email = "anangk97@gmail.com",
            firebaseToken = "",
            photo = "https://lh3.googleusercontent.com/a-/AOh14Gj4pSeYhgSOZRV2Nf0KdnK5JRHW06TGZvmlHLmn=s96-c",
            providerName = ""
        )
        userInternalSetter.postValue(user)
    }
}