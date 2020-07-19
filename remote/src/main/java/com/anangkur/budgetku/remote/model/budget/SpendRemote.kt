package com.anangkur.budgetku.remote.model.budget

data class SpendRemote(
    val image: String = "",
    val title: String = "",
    val date: String = "",
    val spend: Int = 0,
    val idProject: String = "",
    val idCategory: String = ""
)