package com.anangkur.budgetku.data.source.budget

import com.anangkur.budgetku.data.repository.budget.BudgetDataStore
import com.anangkur.budgetku.data.repository.budget.BudgetLocal
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.model.budget.Category
import com.anangkur.budgetku.domain.model.budget.CategoryProject

class BudgetLocalDataStore(
    private val budgetLocal: BudgetLocal
): BudgetDataStore {

    companion object{
        private var INSTANCE: BudgetLocalDataStore? = null
        fun getInstance(budgetLocal: BudgetLocal) = INSTANCE
            ?: BudgetLocalDataStore(
                budgetLocal
            )
    }

    override fun createProject(
        title: String,
        startDate: String,
        endDate: String,
        category: List<CategoryProject>,
        listener: BaseFirebaseListener<Boolean>
    ) {
        throw UnsupportedOperationException()
    }

    override fun getCategory(listener: BaseFirebaseListener<List<Category>>) {
        throw UnsupportedOperationException()
    }
}