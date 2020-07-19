package com.anangkur.budgetku.domain.model.budget

data class Spend(
    val image: String,
    val title: String,
    val date: String,
    val spend: Int,
    val idProject: String
)