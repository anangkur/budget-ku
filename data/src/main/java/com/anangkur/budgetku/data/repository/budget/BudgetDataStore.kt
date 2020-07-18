package com.anangkur.budgetku.data.repository.budget

import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.model.budget.Category
import com.anangkur.budgetku.domain.model.budget.CategoryProject
import com.anangkur.budgetku.domain.model.budget.Project

interface BudgetDataStore {
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
    fun getProject(
        listener: BaseFirebaseListener<List<Project>>
    )
}