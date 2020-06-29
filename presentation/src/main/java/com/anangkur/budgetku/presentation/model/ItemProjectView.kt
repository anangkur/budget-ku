package com.anangkur.budgetku.presentation.model

data class ItemProjectView(
    val title: String? = "",
    val spendPercentage: String? = "",
    val period: String? = "",
    val progress: Int? = 0
)