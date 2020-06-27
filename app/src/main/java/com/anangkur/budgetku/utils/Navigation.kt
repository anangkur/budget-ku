package com.anangkur.budgetku.utils

import android.app.Activity
import android.content.Intent

object Navigation {
    private const val NEWS_ACTIVITY = "com.anangkur.budgetku.news.NewsActivity"
    private const val SIGN_IN_ACTIVITY = "com.anangkur.budgetku.auth.view.signIn"
    private const val HOME_ACTIVITY = "com.anangkur.budgetku.dashboard.view.HomeActivity"

    fun Activity.goToHomeActivity() {
        startActivity(Intent(this, Class.forName(HOME_ACTIVITY)))
    }

    fun Activity.goToSignInActivity() {
        startActivity(Intent(this, Class.forName(SIGN_IN_ACTIVITY)))
    }

    fun Activity.goToNewsActivity() {
        startActivity(Intent(this, Class.forName(NEWS_ACTIVITY)))
    }
}