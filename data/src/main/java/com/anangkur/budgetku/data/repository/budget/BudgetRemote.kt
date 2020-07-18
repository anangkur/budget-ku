package com.anangkur.budgetku.data.repository.budget

import com.anangkur.budgetku.data.BaseFirebaseListener
import com.anangkur.budgetku.data.model.budget.CategoryEntity
import com.anangkur.budgetku.data.model.budget.CategoryProjectEntity
import com.anangkur.budgetku.data.model.budget.ProjectEntity

interface BudgetRemote {
    fun createProject(
        title: String,
        startDate: String,
        endDate: String,
        category: List<CategoryProjectEntity>,
        listener: BaseFirebaseListener<Boolean>
    )

    fun getCategory(
        listener: BaseFirebaseListener<List<CategoryEntity>>
    )

    fun getProject(
        listener: BaseFirebaseListener<List<ProjectEntity>>
    )
}