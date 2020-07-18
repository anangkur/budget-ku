package com.anangkur.budgetku.domain.repository

import android.net.Uri
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.model.auth.User
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun getUser(user: FirebaseUser, listener: BaseFirebaseListener<User?>)
    suspend fun getUser(listener: BaseFirebaseListener<User?>)
    suspend fun createUser(user: FirebaseUser, firebaseToken: String, listener: BaseFirebaseListener<User>)
    suspend fun signInWithGoogle(acct: GoogleSignInAccount?, listener: BaseFirebaseListener<FirebaseUser>)
    suspend fun signInEmail(email: String, password: String, listener: BaseFirebaseListener<FirebaseUser?>)
    suspend fun signUp(name: String, email: String, password: String, listener: BaseFirebaseListener<FirebaseUser>)
    suspend fun resetPassword(email: String, listener: BaseFirebaseListener<String>)
    suspend fun editPassword(newPassword: String, listener: BaseFirebaseListener<Boolean>)
    suspend fun reAuthenticate(oldPassword: String, listener: BaseFirebaseListener<Boolean>)
    suspend fun logout(listener: BaseFirebaseListener<Boolean>)
    suspend fun editProfile(user: User, listener: BaseFirebaseListener<User>)
    suspend fun uploadImage(image: Uri, listener: BaseFirebaseListener<Uri>)
    suspend fun checkUserLogin(listener: BaseFirebaseListener<Boolean>)

    fun saveFirebaseToken(firebaseToken: String)
    fun loadFirebaseToken(): String
}