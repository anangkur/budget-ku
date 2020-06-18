package com.anangkur.budgetku.presentation.features.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditPasswordViewModel(private val repository: AuthRepository): ViewModel() {

    val progressEditPassword = MutableLiveData<Boolean>()
    val successEditPassword = MutableLiveData<Boolean>()
    val errorEditPassword = MutableLiveData<String>()
    private fun editPassword(newPassword: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.editPassword(newPassword, object: BaseFirebaseListener<Boolean> {
                override fun onLoading(isLoading: Boolean) {
                    progressEditPassword.postValue(isLoading)
                }
                override fun onSuccess(data: Boolean) {
                    successEditPassword.postValue(data)
                }
                override fun onFailed(errorMessage: String) {
                    errorEditPassword.postValue(errorMessage)
                }
            })
        }
    }

    val errorAuth = MutableLiveData<String>()
    fun reAuthenticate(oldPassword: String, newPassword: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.reAuthenticate(oldPassword, object: BaseFirebaseListener<Boolean>{
                override fun onLoading(isLoading: Boolean) {
                    progressEditPassword.postValue(isLoading)
                }
                override fun onSuccess(data: Boolean) {
                    editPassword(newPassword)
                }
                override fun onFailed(errorMessage: String) {
                    errorAuth.postValue(errorMessage)
                }
            })
        }
    }
}