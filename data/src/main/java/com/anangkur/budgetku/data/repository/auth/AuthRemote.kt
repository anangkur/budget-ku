package com.anangkur.budgetku.data.repository.auth

import android.net.Uri
import com.anangkur.budgetku.data.BaseFirebaseListener
import com.anangkur.budgetku.data.model.auth.UserEntity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser

interface AuthRemote {
    suspend fun getUser(user: FirebaseUser, listener: BaseFirebaseListener<UserEntity?>)
    suspend fun getUser(listener: BaseFirebaseListener<UserEntity?>)
    suspend fun createUser(user: FirebaseUser, firebaseToken: String, listener: BaseFirebaseListener<UserEntity>)
    suspend fun signInWithGoogle(acct: GoogleSignInAccount?, listener: BaseFirebaseListener<FirebaseUser>)
    suspend fun signInEmail(email: String, password: String, listener: BaseFirebaseListener<FirebaseUser?>)
    suspend fun signUp(name: String, email: String, password: String, listener: BaseFirebaseListener<FirebaseUser>)
    suspend fun resetPassword(email: String, listener: BaseFirebaseListener<String>)
    suspend fun editPassword(newPassword: String, listener: BaseFirebaseListener<Boolean>)
    suspend fun reAuthenticate(oldPassword: String, listener: BaseFirebaseListener<Boolean>)
    suspend fun logout(listener: BaseFirebaseListener<Boolean>)
    suspend fun editProfile(user: UserEntity, listener: BaseFirebaseListener<UserEntity>)
    suspend fun uploadImage(image: Uri, listener: BaseFirebaseListener<Uri>)
    suspend fun checkUserLogin(listener: BaseFirebaseListener<Boolean>)
}