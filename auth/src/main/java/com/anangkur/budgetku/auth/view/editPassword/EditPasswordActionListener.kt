package com.anangkur.budgetku.auth.view.editPassword

interface EditPasswordActionListener {
    fun onClickSave(oldPassword: String?, newPassword: String?, confirmPassword: String?)
}