package com.anangkur.budgetku.auth.view.signUp

interface SignUpActionListener {
    fun onClickSignUp(name: String?, email: String?, password: String?, confirmPassword: String?)
    fun onClickSignIn()
    fun onClickSignUpGoogle()
}