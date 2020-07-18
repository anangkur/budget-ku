package com.anangkur.budgetku.remote.model.budget

data class CategoryProjectRemote(
    val id: String = "",
    val title: String = "",
    val image: String = "",
    val value: Double = 0.0,
    val spend: Double = 0.0
)