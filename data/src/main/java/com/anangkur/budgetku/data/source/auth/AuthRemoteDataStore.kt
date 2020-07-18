package com.anangkur.budgetku.data.source.auth

import android.net.Uri
import com.anangkur.budgetku.data.BaseFirebaseListener
import com.anangkur.budgetku.data.mapper.auth.UserMapper
import com.anangkur.budgetku.data.model.auth.UserEntity
import com.anangkur.budgetku.data.repository.auth.AuthDataSource
import com.anangkur.budgetku.data.repository.auth.AuthRemote
import com.anangkur.budgetku.domain.model.auth.User
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import java.lang.UnsupportedOperationException

class AuthRemoteDataStore(
    private val authRemote: AuthRemote,
    private val userMapper: UserMapper
): AuthDataSource {

    companion object{
        private var INSTANCE: AuthRemoteDataStore? = null
        fun getInstance(authRemote: AuthRemote) = INSTANCE
            ?: AuthRemoteDataStore(
                authRemote,
                UserMapper.getInstance()
            )
    }

    override suspend fun getUser(user: FirebaseUser, listener: BaseFirebaseListener<User?>) {
        authRemote.getUser(user, object: BaseFirebaseListener<UserEntity?> {
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: UserEntity?) {
                listener.onSuccess(data?.let { userMapper.mapFromEntity(it) })
            }
            override fun onFailed(errorMessage: String) {
                listener.onFailed(errorMessage)
            }
        })
    }

    override suspend fun getUser(listener: BaseFirebaseListener<User?>) {
        authRemote.getUser(object: BaseFirebaseListener<UserEntity?> {
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: UserEntity?) {
                data?.let { listener.onSuccess(userMapper.mapFromEntity(data)) }
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
        authRemote.createUser(user, firebaseToken, object: BaseFirebaseListener<UserEntity> {
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: UserEntity) {
                listener.onSuccess(userMapper.mapFromEntity(data))
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
        authRemote.signInWithGoogle(acct, listener)
    }

    override suspend fun signInEmail(
        email: String,
        password: String,
        listener: BaseFirebaseListener<FirebaseUser?>
    ) {
        authRemote.signInEmail(email, password, listener)
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String,
        listener: BaseFirebaseListener<FirebaseUser>
    ) {
        authRemote.signUp(name, email, password, listener)
    }

    override suspend fun resetPassword(email: String, listener: BaseFirebaseListener<String>) {
        authRemote.resetPassword(email, listener)
    }

    override suspend fun editPassword(
        newPassword: String,
        listener: BaseFirebaseListener<Boolean>
    ) {
        authRemote.editPassword(newPassword, listener)
    }

    override suspend fun reAuthenticate(
        oldPassword: String,
        listener: BaseFirebaseListener<Boolean>
    ) {
        authRemote.reAuthenticate(oldPassword, listener)
    }

    override suspend fun logout(listener: BaseFirebaseListener<Boolean>) {
        authRemote.logout(listener)
    }

    override suspend fun editProfile(user: User, listener: BaseFirebaseListener<User>) {
        authRemote.editProfile(userMapper.mapToEntity(user), object: BaseFirebaseListener<UserEntity> {
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: UserEntity) {
                listener.onSuccess(userMapper.mapFromEntity(data))
            }
            override fun onFailed(errorMessage: String) {
                listener.onFailed(errorMessage)
            }
        })
    }

    override suspend fun uploadImage(image: Uri, listener: BaseFirebaseListener<Uri>) {
        authRemote.uploadImage(image, listener)
    }

    override suspend fun checkUserLogin(listener: BaseFirebaseListener<Boolean>) {
        authRemote.checkUserLogin(listener)
    }

    override fun saveFirebaseToken(firebaseToken: String) {
        throw UnsupportedOperationException()
    }

    override fun loadFirebaseToken(): String {
        throw UnsupportedOperationException()
    }

}