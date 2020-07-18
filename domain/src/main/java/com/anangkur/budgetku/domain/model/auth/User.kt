package com.anangkur.budgetku.domain.model.auth

data class User(
    var userId: String = "",
    var email: String = "",
    var name: String = "",
    var height: Int = 0,
    var weight: Int = 0,
    var photo: String = "",
    var providerName: String = "",
    var firebaseToken: String = ""
)