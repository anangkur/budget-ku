package com.anangkur.budgetku.data.repository.budget

import com.anangkur.budgetku.data.BaseFirebaseListener
import com.anangkur.budgetku.data.model.budget.CategoryEntity
import com.anangkur.budgetku.data.model.budget.CategoryProjectEntity
import com.anangkur.budgetku.data.model.budget.ProjectEntity
import com.anangkur.budgetku.data.model.budget.SpendEntity

interface BudgetRemote {
    fun createProject(
        idProject: String?,
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
    fun createSpend(
        spendEntity: SpendEntity,
        listener: BaseFirebaseListener<Boolean>
    )
    fun getListSpend(
        idProject: String,
        idCategory: String?,
        listener: BaseFirebaseListener<List<SpendEntity>>
    )
    fun getProjectDetail(
        idProject: String,
        listener: BaseFirebaseListener<ProjectEntity>
    )
    fun deleteProject(
        idProject: String,
        listener: BaseFirebaseListener<Boolean>
    )
}