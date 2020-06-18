package com.anangkur.budgetku.auth.view.signIn

interface SignInActionListener {
    fun onClickSignIn(email: String?, password: String?)
    fun onClickForgot()
    fun onClickSignUp()
    fun onClickGoogle()
}