package com.anangkur.budgetku.presentation.features.app

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.repository.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(private val repository: AuthRepository): ViewModel(){

    val progressGetProfile = MutableLiveData<Boolean>()
    val successGetProfile = MutableLiveData<Boolean>()
    val errorGetProfile = MutableLiveData<String>()
    fun checkLogin(){
        CoroutineScope(Dispatchers.IO).launch {
            repository.checkUserLogin(object: BaseFirebaseListener<Boolean> {
                override fun onLoading(isLoading: Boolean) {
                    progressGetProfile.postValue(isLoading)
                }
                override fun onSuccess(data: Boolean) {
                    successGetProfile.postValue(data)
                }
                override fun onFailed(errorMessage: String) {
                    errorGetProfile.postValue(errorMessage)
                }
            })
        }
    }

    private fun saveFirebaseToken(firebaseToken: String){
        repository.saveFirebaseToken(firebaseToken)
    }

    fun getTokenFirebase(){
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener { task: Task<InstanceIdResult?> ->
                if (!task.isSuccessful) {
                    Log.w("SplashScreenActivity", "getInstanceId failed", task.exception)
                    return@addOnCompleteListener
                }else{
                    val token = task.result?.token
                    Log.d("SplashScreenActivity", token?:"empty token")
                    saveFirebaseToken(token?:"empty token")
                }
            }
    }
}