package com.anangkur.budgetku.presentation.features.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.model.auth.User
import com.anangkur.budgetku.domain.model.budget.Project
import com.anangkur.budgetku.domain.repository.AuthRepository
import com.anangkur.budgetku.domain.repository.BudgetRepository
import com.anangkur.budgetku.presentation.mapper.auth.UserMapper
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
    private val userMapper = UserMapper.getInstance()

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

    val loadingGetUser = MutableLiveData<Boolean>()
    val successGetUser = MutableLiveData<UserView>()
    val errorGetUser = MutableLiveData<String>()
    fun getUser() {
        CoroutineScope(Dispatchers.IO).launch {
            authRepository.getUser(object : BaseFirebaseListener<User?> {
                override fun onLoading(isLoading: Boolean) {
                    loadingGetUser.postValue(isLoading)
                }
                override fun onSuccess(data: User?) {
                    successGetUser.postValue(userMapper.mapToView(data ?: User()))
                }
                override fun onFailed(errorMessage: String) {
                    errorGetUser.postValue(errorMessage)
                }

            })
        }
    }
}