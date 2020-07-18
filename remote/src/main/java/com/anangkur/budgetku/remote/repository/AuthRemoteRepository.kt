package com.anangkur.budgetku.remote.repository

import android.net.Uri
import com.anangkur.budgetku.data.BaseFirebaseListener
import com.anangkur.budgetku.data.model.auth.UserEntity
import com.anangkur.budgetku.data.repository.auth.AuthRemote
import com.anangkur.budgetku.remote.Consts
import com.anangkur.budgetku.remote.mapper.auth.UserMapper
import com.anangkur.budgetku.remote.model.auth.UserRemoteModel
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.storage.FirebaseStorage

class AuthRemoteRepository(
    private val userMapper: UserMapper,
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
): AuthRemote {

    companion object{
        private var INSTANCE: AuthRemoteRepository? = null
        fun getInstance() = INSTANCE
            ?: AuthRemoteRepository(
                UserMapper.getInstance(),
                FirebaseAuth.getInstance(),
                FirebaseFirestore.getInstance(),
                FirebaseStorage.getInstance()
            )
    }

    override suspend fun getUser(
        user: FirebaseUser,
        listener: BaseFirebaseListener<UserEntity?>
    ) {
        try {
            listener.onLoading(true)
            firestore.collection(Consts.COLLECTION_USER)
                .document(user.uid)
                .get()
                .addOnSuccessListener {
                    listener.onLoading(false)
                    val userFirestore = it.toObject<UserRemoteModel>()
                    if (userFirestore != null && it.contains("firebaseToken")){
                        listener.onSuccess(userMapper.mapFromRemote(userFirestore))
                    }else{
                        listener.onSuccess(null)
                    }
                }
                .addOnFailureListener {
                    it.printStackTrace()
                    listener.onLoading(false)
                    listener.onFailed(it.message?:"")
                }
        }catch (e: Exception){
            e.printStackTrace()
            listener.onLoading(false)
            listener.onFailed(e.message?:"")
        }
    }

    override suspend fun getUser(listener: BaseFirebaseListener<UserEntity?>) {
        try {
            val user = firebaseAuth.currentUser!!
            listener.onLoading(true)
            firestore.collection(Consts.COLLECTION_USER)
                .document(user.uid)
                .get()
                .addOnSuccessListener {
                    listener.onLoading(false)
                    val userFirestore = it.toObject<UserRemoteModel>()
                    if (userFirestore != null && it.contains("firebaseToken")){
                        listener.onSuccess(userMapper.mapFromRemote(userFirestore))
                    }else{
                        listener.onSuccess(null)
                    }
                }
                .addOnFailureListener {
                    it.printStackTrace()
                    listener.onLoading(false)
                    listener.onFailed(it.message?:"")
                }
        }catch (e: Exception){
            e.printStackTrace()
            listener.onLoading(false)
            listener.onFailed(e.message?:"")
        }
    }

    override suspend fun createUser(
        user: FirebaseUser,
        firebaseToken: String,
        listener: BaseFirebaseListener<UserEntity>
    ) {
        try {
            listener.onLoading(true)
            val userMap = UserRemoteModel(
                userId = user.uid,
                email = user.email?:"",
                name = user.displayName?:"",
                height = 0,
                weight = 0,
                photo = user.photoUrl.toString(),
                providerName = user.providerData[user.providerData.size-1].providerId,
                firebaseToken = firebaseToken
            )
            firestore.collection(Consts.COLLECTION_USER)
                .document(userMap.userId)
                .set(userMap)
                .addOnSuccessListener {
                    listener.onLoading(false)
                    listener.onSuccess(userMapper.mapFromRemote(userMap))
                }
                .addOnFailureListener { exception ->
                    exception.printStackTrace()
                    listener.onLoading(false)
                    listener.onFailed(exception.message?:"")
                }
        }catch (e: Exception){
            e.printStackTrace()
            listener.onLoading(false)
            listener.onFailed(e.message?:"")
        }
    }

    override suspend fun signInWithGoogle(
        acct: com.google.android.gms.auth.api.signin.GoogleSignInAccount?,
        listener: BaseFirebaseListener<FirebaseUser>
    ) {
        try {
            listener.onLoading(true)
            val credential = GoogleAuthProvider.getCredential(acct?.idToken, null)
            firebaseAuth.signInWithCredential(credential).addOnCompleteListener{ task ->
                listener.onLoading(false)
                if (task.isSuccessful) {
                    listener.onSuccess(task.result?.user!!)
                } else {
                    task.exception?.printStackTrace()
                    listener.onFailed(task.exception?.message?:"")
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
            listener.onLoading(false)
            listener.onFailed(e.message?:"")
        }
    }

    override suspend fun signInEmail(
        email: String,
        password: String,
        listener: BaseFirebaseListener<FirebaseUser?>
    ) {
        try {
            listener.onLoading(true)
            firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    listener.onLoading(false)
                    if (it.isSuccessful){
                        listener.onSuccess(it.result?.user)
                    }else{
                        it.exception?.printStackTrace()
                        listener.onFailed(it.exception?.message?:"")
                    }
                }
        }catch (e: Exception){
            e.printStackTrace()
            listener.onLoading(false)
            listener.onFailed(e.message?:"")
        }
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String,
        listener: BaseFirebaseListener<FirebaseUser>
    ) {
        try {
            listener.onLoading(true)
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    listener.onLoading(false)
                    if (it.isSuccessful){
                        val profileUpdate = UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build()
                        it.result?.user?.updateProfile(profileUpdate)?.addOnCompleteListener {updateProfile ->
                            if (updateProfile.isSuccessful){
                                listener.onSuccess(it.result?.user!!)
                            }else{
                                listener.onFailed(updateProfile.exception?.message?:"")
                            }
                        }
                    }else{
                        it.exception?.printStackTrace()
                        listener.onFailed(it.exception?.message?:"")
                    }
                }
        }catch (e: Exception){
            e.printStackTrace()
            listener.onLoading(false)
            listener.onFailed(e.message?:"")
        }
    }

    override suspend fun resetPassword(email: String, listener: BaseFirebaseListener<String>) {
        try {
            listener.onLoading(true)
            firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener {
                    listener.onLoading(false)
                    if (it.isSuccessful){
                        listener.onSuccess("Email sent!")
                    }else{
                        it.exception?.printStackTrace()
                        listener.onFailed(it.exception?.message?:"")
                    }
                }
        }catch (e: Exception){
            e.printStackTrace()
            listener.onLoading(false)
            listener.onFailed(e.message?:"")
        }
    }

    override suspend fun editPassword(
        newPassword: String,
        listener: BaseFirebaseListener<Boolean>
    ) {
        try {
            listener.onLoading(true)
            val user = firebaseAuth.currentUser
            user?.updatePassword(newPassword)?.addOnCompleteListener {task ->
                listener.onLoading(false)
                if (task.isSuccessful){
                    listener.onSuccess(true)
                }else{
                    task.exception?.printStackTrace()
                    listener.onFailed(task.exception?.message?:"")
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
            listener.onLoading(false)
            listener.onFailed(e.message?:"")
        }
    }

    override suspend fun reAuthenticate(
        oldPassword: String,
        listener: BaseFirebaseListener<Boolean>
    ) {
        try {
            listener.onLoading(true)
            val user = firebaseAuth.currentUser
            val credential = EmailAuthProvider.getCredential(user?.email?:"", oldPassword)
            user?.reauthenticate(credential)?.addOnCompleteListener {
                listener.onLoading(false)
                if (it.isSuccessful){
                    listener.onSuccess(true)
                }else{
                    it.exception?.printStackTrace()
                    listener.onFailed(it.exception?.message?:"")
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
            listener.onLoading(false)
            listener.onFailed(e.message?:"")
        }
    }

    override suspend fun logout(listener: BaseFirebaseListener<Boolean>) {
        try {
            listener.onLoading(true)
            firebaseAuth.signOut()
            listener.onLoading(false)
            listener.onSuccess(true)
        }catch (e: Exception){
            e.printStackTrace()
            listener.onLoading(false)
            listener.onFailed(e.message?:"")
        }
    }

    override suspend fun uploadImage(image: Uri, listener: BaseFirebaseListener<Uri>) {
        try {
            listener.onLoading(true)
            val fileName = image.lastPathSegment?:""
            val extension = fileName.substring(fileName.lastIndexOf("."))
            val user = firebaseAuth.currentUser
            val storageReference = storage.reference
                .child(Consts.STORAGE_PROFILE_PHOTO)
                .child(user?.uid?:"")
                .child("${user?.uid}$extension")
            storageReference.putFile(image)
                .addOnProgressListener {
                    listener.onLoading(true)
                }.continueWithTask { task ->
                    if (!task.isSuccessful) {
                        throw task.exception!!
                    }
                    storageReference.downloadUrl
                }.addOnSuccessListener {
                    listener.onLoading(false)
                    listener.onSuccess(it)
                }
                .addOnFailureListener {
                    it.printStackTrace()
                    listener.onLoading(false)
                    listener.onFailed(it.message?:"")
                }
        }catch (e: Exception){
            e.printStackTrace()
            listener.onLoading(false)
            listener.onFailed(e.message?:"")
        }
    }

    override suspend fun checkUserLogin(listener: BaseFirebaseListener<Boolean>) {
        try {
            listener.onLoading(true)
            val user = firebaseAuth.currentUser
            if (user == null){
                listener.onSuccess(false)
            }else{
                listener.onSuccess(true)
            }
            listener.onLoading(false)
        }catch (e: Exception){
            e.printStackTrace()
            listener.onFailed(e.message?:"")
            listener.onLoading(false)
        }
    }

    override suspend fun editProfile(
        user: UserEntity,
        listener: BaseFirebaseListener<UserEntity>
    ) {
        try {
            listener.onLoading(true)
            val userFirebase = firebaseAuth.currentUser
            firestore
                .collection(Consts.COLLECTION_USER)
                .document(userFirebase?.uid?:"")
                .set(userMapper.mapToRemote(user))
                .addOnSuccessListener {
                    listener.onLoading(false)
                    listener.onSuccess(user)
                }
                .addOnFailureListener { exeption ->
                    exeption.printStackTrace()
                    listener.onLoading(false)
                    listener.onFailed(exeption.message?:"")
                }
        }catch (e: Exception){
            e.printStackTrace()
            listener.onLoading(false)
            listener.onFailed(e.message?:"")
        }
    }

}