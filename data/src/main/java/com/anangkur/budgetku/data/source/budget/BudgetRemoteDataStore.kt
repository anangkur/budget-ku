package com.anangkur.budgetku.data.source.budget

import com.anangkur.budgetku.data.mapper.budget.CategoryProjectMapper
import com.anangkur.budgetku.data.model.budget.CategoryProjectEntity
import com.anangkur.budgetku.data.repository.budget.BudgetDataSource
import com.anangkur.budgetku.data.repository.budget.BudgetRemote
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.model.budget.CategoryProject

class BudgetRemoteDataStore(
    private val budgetRemote: BudgetRemote
): BudgetDataSource {

    private val categoryProjectMapper = CategoryProjectMapper.getInstance()

    companion object{
        private var INSTANCE: BudgetRemoteDataStore? = null
        fun getInstance(budgetRemote: BudgetRemote) = INSTANCE
            ?: BudgetRemoteDataStore(
                budgetRemote
            )
    }

    override fun createProject(
        title: String,
        startDate: String,
        endDate: String,
        category: List<CategoryProject>,
        listener: BaseFirebaseListener<Boolean>
    ) {
        budgetRemote.createProject(title, startDate, endDate, category.map {
            categoryProjectMapper.mapToEntity(it)
        }, object: com.anangkur.budgetku.data.BaseFirebaseListener<Boolean>{
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }

            override fun onSuccess(data: Boolean) {
                listener.onSuccess(data)
            }

            override fun onFailed(errorMessage: String) {
                listener.onFailed(errorMessage)
            }

        })
    }
}