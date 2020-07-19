package com.anangkur.budgetku.presentation.model.budget

data class SpendView(
    val idProject: String,
    val image: String,
    val title: String,
    val date: String,
    val spend: Int
)