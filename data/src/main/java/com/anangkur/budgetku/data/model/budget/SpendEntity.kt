package com.anangkur.budgetku.data.model.budget

data class SpendEntity(
    val image: String,
    val title: String,
    var date: String,
    val spend: Int,
    val idProject: String,
    val idCategory: String
)