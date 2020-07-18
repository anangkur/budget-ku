package com.anangkur.budgetku.remote.model.budget

data class CategoryRemote(
    val title: String = "",
    val image: String = "",
    val child: List<CategoryRemote> = emptyList()
)