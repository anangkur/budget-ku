package com.anangkur.budgetku.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProjectIntent(
    val id: String,
    val title: String,
    val totalBudget: Double,
    val totalSpend: Double,
    val totalRemaining: Double,
    val spendPercentage: String,
    val endDate: String,
    val startDate: String,
    val period: String,
    val progress: Int,
    val listCategory: List<CategoryProjectIntent>
): Parcelable