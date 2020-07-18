package com.anangkur.budgetku.budget.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryUiModel(
    val title: String,
    val image: String,
    val child: List<CategoryUiModel> = emptyList()
): Parcelable