package com.anangkur.budgetku.dashboard.model

data class ProjectIntent(
    val title: String? = "",
    val spendPercentage: String? = "",
    val period: String? = "",
    val progress: Int? = 0
)