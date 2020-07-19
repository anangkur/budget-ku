package com.anangkur.budgetku.budget.model

data class SpendUiModel(
    val image: String,
    val title: String,
    val date: String,
    val spend: Int,
    val idProject: String,
    val idCategory: String
)