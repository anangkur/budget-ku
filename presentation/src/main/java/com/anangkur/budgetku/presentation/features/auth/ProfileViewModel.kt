package com.anangkur.budgetku.presentation.features.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.model.User
import com.anangkur.budgetku.domain.repository.AuthRepository
import com.anangkur.budgetku.presentation.mapper.UserMapper
import com.anangkur.budgetku.presentation.model.auth.UserView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: AuthRepository): ViewModel() {

    private val userMapper = UserMapper.getInstance()

    val progressGetProfile = MutableLiveData<Boolean>()
    val successGetProfile = MutableLiveData<UserView>()
    val errorGetProfile = MutableLiveData<String>()
    fun getUserProfile(){
        CoroutineScope(Dispatchers.IO).launch {
            repository.getUser(object: BaseFirebaseListener<User?> {
                override fun onLoading(isLoading: Boolean) {
                    progressGetProfile.postValue(isLoading)
                }
                override fun onSuccess(data: User?) {
                    successGetProfile.postValue(data?.let { userMapper.mapToView(it) })
                }
                override fun onFailed(errorMessage: String) {
                    errorGetProfile.postValue(errorMessage)
                }
            })
        }
    }

    val progressLogout = MutableLiveData<Boolean>()
    val successLogout = MutableLiveData<Boolean>()
    val errorLogout = MutableLiveData<String>()
    fun logout(){
        CoroutineScope(Dispatchers.IO).launch {
            repository.logout(object: BaseFirebaseListener<Boolean>{
                override fun onLoading(isLoading: Boolean) {
                    progressLogout.postValue(isLoading)
                }
                override fun onSuccess(data: Boolean) {
                    successLogout.postValue(data)
                }
                override fun onFailed(errorMessage: String) {
                    errorLogout.postValue(errorMessage)
                }
            })
        }
    }
}