package com.anangkur.budgetku.domain.repository

import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.model.budget.Category
import com.anangkur.budgetku.domain.model.budget.CategoryProject

interface BudgetRepository {
    fun createProject(
        title: String,
        startDate: String,
        endDate: String,
        category: List<CategoryProject>,
        listener: BaseFirebaseListener<Boolean>
    )

    fun getCategory(
        listener: BaseFirebaseListener<List<Category>>
    )
}