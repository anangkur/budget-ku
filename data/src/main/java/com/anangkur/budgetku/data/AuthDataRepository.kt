package com.anangkur.budgetku.data

import android.net.Uri
import com.anangkur.budgetku.data.repository.auth.AuthLocal
import com.anangkur.budgetku.data.repository.auth.AuthRemote
import com.anangkur.budgetku.data.source.auth.AuthDataStoreFactory
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.model.User
import com.anangkur.budgetku.domain.repository.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import com.anangkur.budgetku.data.BaseFirebaseListener as dataListener

class AuthDataRepository (private val factory: AuthDataStoreFactory): AuthRepository {

    companion object{
        private var INSTANCE: AuthDataRepository? = null
        fun getInstance(
            authLocal: AuthLocal,
            authRemote: AuthRemote
        ) = INSTANCE ?: AuthDataRepository(
            AuthDataStoreFactory.getInstance(authRemote, authLocal)
        )
    }

    override suspend fun getUser(user: FirebaseUser, listener: BaseFirebaseListener<User?>) {
        factory.retrieveRemoteDataStore().getUser(user, object: dataListener<User?> {
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: User?) {
                listener.onSuccess(data)
            }
            override fun onFailed(errorMessage: String) {
                listener.onFailed(errorMessage)
            }
        })
    }

    override suspend fun getUser(listener: BaseFirebaseListener<User?>) {
        factory.retrieveRemoteDataStore().getUser(object: dataListener<User?> {
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: User?) {
                listener.onSuccess(data)
            }
            override fun onFailed(errorMessage: String) {
                listener.onFailed(errorMessage)
            }
        })
    }

    override suspend fun createUser(
        user: FirebaseUser,
        firebaseToken: String,
        listener: BaseFirebaseListener<User>
    ) {
        factory.retrieveRemoteDataStore().createUser(user, firebaseToken, object: dataListener<User> {
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: User) {
                listener.onSuccess(data)
            }
            override fun onFailed(errorMessage: String) {
                listener.onFailed(errorMessage)
            }
        })
    }

    override suspend fun signInWithGoogle(
        acct: GoogleSignInAccount?,
        listener: BaseFirebaseListener<FirebaseUser>
    ) {
        factory.retrieveRemoteDataStore().signInWithGoogle(acct, object: dataListener<FirebaseUser> {
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: FirebaseUser) {
                listener.onSuccess(data)
            }
            override fun onFailed(errorMessage: String) {
                listener.onFailed(errorMessage)
            }
        })
    }

    override suspend fun signInEmail(
        email: String,
        password: String,
        listener: BaseFirebaseListener<FirebaseUser?>
    ) {
        factory.retrieveRemoteDataStore().signInEmail(email, password, object: dataListener<FirebaseUser?> {
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: FirebaseUser?) {
                listener.onSuccess(data)
            }
            override fun onFailed(errorMessage: String) {
                listener.onFailed(errorMessage)
            }
        })
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String,
        listener: BaseFirebaseListener<FirebaseUser>
    ) {
        factory.retrieveRemoteDataStore().signUp(name, email, password, object: dataListener<FirebaseUser> {
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: FirebaseUser) {
                listener.onSuccess(data)
            }
            override fun onFailed(errorMessage: String) {
                listener.onFailed(errorMessage)
            }
        })
    }

    override suspend fun resetPassword(email: String, listener: BaseFirebaseListener<String>) {
        factory.retrieveRemoteDataStore().resetPassword(email, object: dataListener<String> {
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: String) {
                listener.onSuccess(data)
            }
            override fun onFailed(errorMessage: String) {
                listener.onFailed(errorMessage)
            }
        })
    }

    override suspend fun editPassword(
        newPassword: String,
        listener: BaseFirebaseListener<Boolean>
    ) {
        factory.retrieveRemoteDataStore().editPassword(newPassword, object: dataListener<Boolean> {
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: Boolean) {
                listener.onSuccess(data)
            }
            override fun onFailed(errorMessage: String) {
                listener.onFailed(errorMessage)
            }
        })
    }

    override suspend fun reAuthenticate(
        oldPassword: String,
        listener: BaseFirebaseListener<Boolean>
    ) {
        factory.retrieveRemoteDataStore().reAuthenticate(oldPassword, object: dataListener<Boolean> {
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: Boolean) {
                listener.onSuccess(data)
            }
            override fun onFailed(errorMessage: String) {
                listener.onFailed(errorMessage)
            }
        })
    }

    override suspend fun logout(listener: BaseFirebaseListener<Boolean>) {
        factory.retrieveRemoteDataStore().logout(object: dataListener<Boolean> {
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: Boolean) {
                listener.onSuccess(data)
            }
            override fun onFailed(errorMessage: String) {
                listener.onFailed(errorMessage)
            }
        })
    }

    override suspend fun editProfile(user: User, listener: BaseFirebaseListener<User>) {
        factory.retrieveRemoteDataStore().editProfile(user, object: dataListener<User> {
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: User) {
                listener.onSuccess(data)
            }
            override fun onFailed(errorMessage: String) {
                listener.onFailed(errorMessage)
            }
        })
    }

    override suspend fun uploadImage(image: Uri, listener: BaseFirebaseListener<Uri>) {
        factory.retrieveRemoteDataStore().uploadImage(image, object: dataListener<Uri> {
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: Uri) {
                listener.onSuccess(data)
            }
            override fun onFailed(errorMessage: String) {
                listener.onFailed(errorMessage)
            }
        })
    }

    override suspend fun checkUserLogin(listener: BaseFirebaseListener<Boolean>) {
        factory.retrieveRemoteDataStore().checkUserLogin(object: dataListener<Boolean> {
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: Boolean) {
                listener.onSuccess(data)
            }
            override fun onFailed(errorMessage: String) {
                listener.onFailed(errorMessage)
            }
        })
    }

    override fun saveFirebaseToken(firebaseToken: String) {
        factory.retrieveLocalDataStore().saveFirebaseToken(firebaseToken)
    }

    override fun loadFirebaseToken(): String {
        return factory.retrieveLocalDataStore().loadFirebaseToken()
    }

}