package com.anangkur.budgetku.presentation.features.auth

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.model.User
import com.anangkur.budgetku.domain.repository.AuthRepository
import com.anangkur.budgetku.presentation.mapper.UserMapper
import com.anangkur.budgetku.presentation.model.UserView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditProfileViewModel(private val repository: AuthRepository): ViewModel() {

    var user: UserView? = null
    private val userMapper = UserMapper.getInstance()

    val progressEditProfile = MutableLiveData<Boolean>()
    val successEditProfile = MutableLiveData<UserView>()
    val errorEditProfile = MutableLiveData<String>()
    fun editProfile(user: UserView){
        CoroutineScope(Dispatchers.IO).launch {
            repository.editProfile(userMapper.mapFromView(user), object: BaseFirebaseListener<User> {
                override fun onLoading(isLoading: Boolean) {
                    progressEditProfile.postValue(isLoading)
                }
                override fun onSuccess(data: User) {
                    successEditProfile.postValue(userMapper.mapToView(data))
                }
                override fun onFailed(errorMessage: String) {
                    errorEditProfile.postValue(errorMessage)
                }
            })
        }
    }

    val progressGetProfile = MutableLiveData<Boolean>()
    val successGetProfile = MutableLiveData<UserView>()
    fun getUserProfile(){
        CoroutineScope(Dispatchers.IO).launch {
            repository.getUser(object: BaseFirebaseListener<User?>{
                override fun onLoading(isLoading: Boolean) {
                    progressGetProfile.postValue(isLoading)
                }
                override fun onSuccess(data: User?) {
                    successGetProfile.postValue(data?.let { userMapper.mapToView(it) })
                }
                override fun onFailed(errorMessage: String) {
                    errorEditProfile.postValue(errorMessage)
                }
            })
        }
    }

    val progressUploadImage = MutableLiveData<Boolean>()
    val successUploadImage = MutableLiveData<Uri>()
    fun uploadImage(image: Uri){
        CoroutineScope(Dispatchers.IO).launch {
            repository.uploadImage(image, object: BaseFirebaseListener<Uri>{
                override fun onLoading(isLoading: Boolean) {
                    progressUploadImage.postValue(isLoading)
                }
                override fun onSuccess(data: Uri) {
                    successUploadImage.postValue(data)
                }
                override fun onFailed(errorMessage: String) {
                    errorEditProfile.postValue(errorMessage)
                }
            })
        }
    }
}