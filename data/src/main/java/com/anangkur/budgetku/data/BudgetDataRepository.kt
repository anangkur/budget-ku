package com.anangkur.budgetku.data

import com.anangkur.budgetku.data.repository.budget.BudgetLocal
import com.anangkur.budgetku.data.repository.budget.BudgetRemote
import com.anangkur.budgetku.data.source.budget.BudgetDataStoreFactory
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.model.budget.Category
import com.anangkur.budgetku.domain.model.budget.CategoryProject
import com.anangkur.budgetku.domain.model.budget.Project
import com.anangkur.budgetku.domain.model.budget.Spend
import com.anangkur.budgetku.domain.repository.BudgetRepository

class BudgetDataRepository(
    private val factory: BudgetDataStoreFactory
) : BudgetRepository {

    companion object{
        private var INSTANCE: BudgetDataRepository? = null
        fun getInstance(
            budgetLocal: BudgetLocal,
            budgetRemote: BudgetRemote
        ) = INSTANCE ?: BudgetDataRepository(
            BudgetDataStoreFactory.getInstance(budgetLocal, budgetRemote)
        )
    }

    override fun createProject(
        idProject: String?,
        title: String,
        startDate: String,
        endDate: String,
        category: List<CategoryProject>,
        listener: BaseFirebaseListener<Boolean>
    ) {
        factory.retrieveRemoteDataStore().createProject(idProject, title, startDate, endDate, category, listener)
    }

    override fun getCategory(listener: BaseFirebaseListener<List<Category>>) {
        factory.retrieveRemoteDataStore().getCategory(listener)
    }

    override fun getProject(listener: BaseFirebaseListener<List<Project>>) {
        factory.retrieveRemoteDataStore().getProject(listener)
    }

    override fun createSpend(spend: Spend, listener: BaseFirebaseListener<Boolean>) {
        factory.retrieveRemoteDataStore().createSpend(spend, listener)
    }

    override fun getListSpend(
        idProject: String,
        idCategory: String?,
        listener: BaseFirebaseListener<List<Spend>>
    ) {
        factory.retrieveRemoteDataStore().getListSpend(idProject, idCategory, listener)
    }

    override fun getProjectDetail(projectId: String, listener: BaseFirebaseListener<Project>) {
        factory.retrieveRemoteDataStore().getProjectDetail(projectId, listener)
    }

    override fun deleteProject(projectId: String, listener: BaseFirebaseListener<Boolean>) {
        factory.retrieveRemoteDataStore().deleteProject(projectId, listener)
    }
}