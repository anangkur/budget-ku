package com.anangkur.budgetku.data

import com.anangkur.budgetku.data.repository.budget.BudgetLocal
import com.anangkur.budgetku.data.repository.budget.BudgetRemote
import com.anangkur.budgetku.data.source.budget.BudgetDataStoreFactory
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.model.budget.Category
import com.anangkur.budgetku.domain.model.budget.CategoryProject
import com.anangkur.budgetku.domain.model.budget.Project
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
        title: String,
        startDate: String,
        endDate: String,
        category: List<CategoryProject>,
        listener: BaseFirebaseListener<Boolean>
    ) {
        factory.retrieveRemoteDataStore().createProject(title, startDate, endDate, category, listener)
    }

    override fun getCategory(listener: BaseFirebaseListener<List<Category>>) {
        factory.retrieveRemoteDataStore().getCategory(listener)
    }

    override fun getProject(listener: BaseFirebaseListener<List<Project>>) {
        factory.retrieveRemoteDataStore().getProject(listener)
    }
}