package com.anangkur.budgetku.data.repository.budget

import com.anangkur.budgetku.data.model.budget.CategoryProjectEntity
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.model.budget.CategoryProject

interface BudgetDataSource {
    fun createProject(
        title: String,
        startDate: String,
        endDate: String,
        category: List<CategoryProject>,
        listener: BaseFirebaseListener<Boolean>
    )
}