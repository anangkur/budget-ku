package com.anangkur.budgetku.auth.model

data class UserIntent(
    var userId: String = "",
    var email: String = "",
    var name: String = "",
    var height: Int = 0,
    var weight: Int = 0,
    var photo: String = "",
    var providerName: String = "",
    var firebaseToken: String = ""
)