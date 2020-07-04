package com.anangkur.budgetku.presentation.model.dashboard

data class ItemProjectView(
    val title: String? = "",
    val spendPercentage: String? = "",
    val period: String? = "",
    val progress: Int? = 0
)