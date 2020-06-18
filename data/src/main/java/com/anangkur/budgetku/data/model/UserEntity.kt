package com.anangkur.budgetku.data.model

data class UserEntity(
    var userId: String = "",
    var email: String = "",
    var name: String = "",
    var height: Int = 0,
    var weight: Int = 0,
    var photo: String = "",
    var providerName: String = "",
    var firebaseToken: String = ""
)