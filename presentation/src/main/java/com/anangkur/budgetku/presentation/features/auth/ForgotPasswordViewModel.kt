package com.anangkur.budgetku.presentation.features.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(private val repository: AuthRepository): ViewModel() {

    val progressForgotPassword = MutableLiveData<Boolean>()
    val successForgotPassword = MutableLiveData<String>()
    val errorForgotPassword = MutableLiveData<String>()
    fun sendResetEmail(email: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.resetPassword(email, object: BaseFirebaseListener<String> {
                override fun onLoading(isLoading: Boolean) {
                    progressForgotPassword.postValue(isLoading)
                }
                override fun onSuccess(data: String) {
                    successForgotPassword.postValue(data)
                }
                override fun onFailed(errorMessage: String) {
                    errorForgotPassword.postValue(errorMessage)
                }
            })
        }
    }
}