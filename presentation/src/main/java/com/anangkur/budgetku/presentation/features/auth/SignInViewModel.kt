package com.anangkur.budgetku.presentation.features.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.model.User
import com.anangkur.budgetku.domain.repository.AuthRepository
import com.anangkur.budgetku.presentation.mapper.UserMapper
import com.anangkur.budgetku.presentation.model.auth.UserView
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInViewModel(private val repository: AuthRepository): ViewModel() {

    private val userMapper = UserMapper.getInstance()

    val progressSignInLive = MutableLiveData<Boolean>()
    val resultSignInLive = MutableLiveData<FirebaseUser>()
    val errorSignInLive = MutableLiveData<String>()
    fun firebaseSignIn(email: String, password: String){
        CoroutineScope(Dispatchers.IO).launch{
            repository.signInEmail(email, password, object: BaseFirebaseListener<FirebaseUser?> {
                override fun onLoading(isLoading: Boolean) {
                    progressSignInLive.postValue(isLoading)
                }
                override fun onSuccess(data: FirebaseUser?) {
                    resultSignInLive.postValue(data)
                }
                override fun onFailed(errorMessage: String) {
                    errorSignInLive.postValue(errorMessage)
                }
            })
        }
    }

    val progressSignInGoogleLive = MutableLiveData<Boolean>()
    fun firebaseSignInWithGoogle(acct: GoogleSignInAccount?) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.signInWithGoogle(acct, object: BaseFirebaseListener<FirebaseUser>{
                override fun onLoading(isLoading: Boolean) {
                    progressSignInGoogleLive.postValue(isLoading)
                }
                override fun onSuccess(data: FirebaseUser) {
                    getUser(data)
                }
                override fun onFailed(errorMessage: String) {
                    errorSignInLive.postValue(errorMessage)
                }
            })
        }
    }

    val successCreateUser = MutableLiveData<UserView>()
    private fun createUser(user: FirebaseUser){
        CoroutineScope(Dispatchers.IO).launch {
            repository.createUser(user, loadFirebaseToken(), object: BaseFirebaseListener<User>{
                override fun onLoading(isLoading: Boolean) {
                    progressSignInGoogleLive.postValue(isLoading)
                }
                override fun onSuccess(data: User) {
                    successCreateUser.postValue(userMapper.mapToView(data))
                }
                override fun onFailed(errorMessage: String) {
                    errorSignInLive.postValue(errorMessage)
                }
            })
        }
    }

    private fun getUser(user: FirebaseUser){
        CoroutineScope(Dispatchers.IO).launch {
            repository.getUser(user, object: BaseFirebaseListener<User?>{
                override fun onLoading(isLoading: Boolean) {
                    progressSignInGoogleLive.postValue(isLoading)
                }
                override fun onSuccess(data: User?) {
                    if (data == null){
                        createUser(user)
                    }else{
                        successCreateUser.postValue(userMapper.mapToView(data))
                    }
                }
                override fun onFailed(errorMessage: String) {
                    errorSignInLive.postValue(errorMessage)
                }
            })
        }
    }

    private fun loadFirebaseToken(): String {
        return repository.loadFirebaseToken()
    }
}