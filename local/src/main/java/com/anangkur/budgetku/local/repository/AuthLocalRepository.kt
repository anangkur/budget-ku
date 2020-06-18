package com.anangkur.budgetku.local.repository

import android.content.Context
import android.content.SharedPreferences
import com.anangkur.budgetku.data.repository.auth.AuthLocal
import com.anangkur.budgetku.local.Const

class AuthLocalRepository(private val preferences: SharedPreferences): AuthLocal {

    companion object{
        private var INSTANCE: AuthLocalRepository? = null
        fun getInstance(context: Context) = INSTANCE
            ?: AuthLocalRepository(
                context.getSharedPreferences(
                    Const.PREF_NAME,
                    Context.MODE_PRIVATE
                )
            )
    }

    override fun saveFirebaseToken(firebaseToken: String) {
        preferences.edit().putString(Const.PREF_FIREBASE_TOKEN, firebaseToken).apply()
    }

    override fun loadFirebaseToken(): String {
        return preferences.getString(Const.PREF_FIREBASE_TOKEN, "") ?: ""
    }
}