package com.anangkur.budgetku.data.source.auth

import android.net.Uri
import com.anangkur.budgetku.data.BaseFirebaseListener
import com.anangkur.budgetku.data.repository.auth.AuthDataSource
import com.anangkur.budgetku.data.repository.auth.AuthLocal
import com.anangkur.budgetku.domain.model.auth.User
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import java.lang.UnsupportedOperationException

class AuthLocalDataStore(private val authLocal: AuthLocal): AuthDataSource {

    companion object{
        private var INSTANCE: AuthLocalDataStore? = null
        fun getInstance(authLocal: AuthLocal) = INSTANCE
            ?: AuthLocalDataStore(authLocal)
    }

    override suspend fun getUser(user: FirebaseUser, listener: BaseFirebaseListener<User?>) {
        throw UnsupportedOperationException()
    }

    override suspend fun getUser(listener: BaseFirebaseListener<User?>) {
        throw UnsupportedOperationException()
    }

    override suspend fun createUser(
        user: FirebaseUser,
        firebaseToken: String,
        listener: BaseFirebaseListener<User>
    ) {
        throw UnsupportedOperationException()
    }

    override suspend fun signInWithGoogle(
        acct: GoogleSignInAccount?,
        listener: BaseFirebaseListener<FirebaseUser>
    ) {
        throw UnsupportedOperationException()
    }

    override suspend fun signInEmail(
        email: String,
        password: String,
        listener: BaseFirebaseListener<FirebaseUser?>
    ) {
        throw UnsupportedOperationException()
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String,
        listener: BaseFirebaseListener<FirebaseUser>
    ) {
        throw UnsupportedOperationException()
    }

    override suspend fun resetPassword(email: String, listener: BaseFirebaseListener<String>) {
        throw UnsupportedOperationException()
    }

    override suspend fun editPassword(
        newPassword: String,
        listener: BaseFirebaseListener<Boolean>
    ) {
        throw UnsupportedOperationException()
    }

    override suspend fun reAuthenticate(
        oldPassword: String,
        listener: BaseFirebaseListener<Boolean>
    ) {
        throw UnsupportedOperationException()
    }

    override suspend fun logout(listener: BaseFirebaseListener<Boolean>) {
        throw UnsupportedOperationException()
    }

    override suspend fun editProfile(user: User, listener: BaseFirebaseListener<User>) {
        throw UnsupportedOperationException()
    }

    override suspend fun uploadImage(image: Uri, listener: BaseFirebaseListener<Uri>) {
        throw UnsupportedOperationException()
    }

    override suspend fun checkUserLogin(listener: BaseFirebaseListener<Boolean>) {
        throw UnsupportedOperationException()
    }

    override fun saveFirebaseToken(firebaseToken: String) {
        authLocal.saveFirebaseToken(firebaseToken)
    }

    override fun loadFirebaseToken(): String {
        return authLocal.loadFirebaseToken()
    }

}