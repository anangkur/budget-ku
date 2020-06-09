package com.anangkur.budgetku.auth.view.userProfile

interface ProfileActionListener {
    fun onClickEditProfile()
    fun onClickEditPassword()
    fun onClickLogout()
    fun onCLickAbout()
    fun onClickImage(imageUrl: String)
}