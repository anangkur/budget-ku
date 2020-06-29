package com.anangkur.budgetku.presentation.model

data class UserView(
    var userId: String = "",
    var email: String = "",
    var name: String = "",
    var photo: String = "",
    var providerName: String = "",
    var firebaseToken: String = ""
)