package com.anangkur.budgetku.data.repository.budget

import com.anangkur.budgetku.data.BaseFirebaseListener
import com.anangkur.budgetku.data.model.budget.CategoryProjectEntity

interface BudgetRemote {
    fun createProject(
        title: String,
        startDate: String,
        endDate: String,
        category: List<CategoryProjectEntity>,
        listener: BaseFirebaseListener<Boolean>
    )
}