package com.anangkur.budgetku.utils

import android.app.Activity
import android.content.Intent

object Navigation {
    private const val NEWS_ACTIVITY = "com.anangkur.budgetku.news.NewsActivity"
    private const val SIGN_IN_ACTIVITY = "com.anangkur.budgetku.auth.view.signIn.SignInActivity"
    private const val HOME_ACTIVITY = "com.anangkur.budgetku.dashboard.view.HomeActivity"
    private const val DETAIL_PROJECT_ACTIVITY = "com.anangkur.budgetku.budget.view.detailProject.DetailProjectActivity"
    private const val DETAIL_SPEND_ACTIVITY = "com.anangkur.budgetku.budget.view.detailSpend.DetailSpendActivity"
    private const val ADD_PROJECT_ACTIVITY = "com.anangkur.budgetku.budget.view.addProject.AddProjectActivity"
    private const val PROFILE_ACTIVITY = "com.anangkur.budgetku.auth.view.userProfile.ProfileActivity"

    fun Activity.goToHomeActivity() {
        startActivity(Intent(this, Class.forName(HOME_ACTIVITY)))
    }

    fun Activity.goToSignInActivity() {
        startActivity(Intent(this, Class.forName(SIGN_IN_ACTIVITY)))
    }

    fun Activity.goToNewsActivity() {
        startActivity(Intent(this, Class.forName(NEWS_ACTIVITY)))
    }

    fun Activity.goToDetailProjectActivity() {
        startActivity(Intent(this, Class.forName(DETAIL_PROJECT_ACTIVITY)))
    }

    fun Activity.goToDetailSpendActivity() {
        startActivity(Intent(this, Class.forName(DETAIL_SPEND_ACTIVITY)))
    }

    fun Activity.goToAddProjectActivity() {
        startActivity(Intent(this, Class.forName(ADD_PROJECT_ACTIVITY)))
    }

    fun Activity.goToProfileActivity() {
        startActivity(Intent(this, Class.forName(PROFILE_ACTIVITY)))
    }
}