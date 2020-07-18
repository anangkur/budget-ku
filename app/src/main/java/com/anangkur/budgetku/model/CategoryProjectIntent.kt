package com.anangkur.budgetku.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryProjectIntent(
    val id: String,
    val title: String,
    val image: String,
    val value: Double,
    val spend: Double,
    val remaining: Double
): Parcelable