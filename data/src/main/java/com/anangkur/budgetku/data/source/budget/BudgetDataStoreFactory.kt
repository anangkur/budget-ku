package com.anangkur.budgetku.data.source.budget

import com.anangkur.budgetku.data.repository.budget.BudgetLocal
import com.anangkur.budgetku.data.repository.budget.BudgetRemote

class BudgetDataStoreFactory(
    private val localDataStore: BudgetLocalDataStore,
    private val remoteDataStore: BudgetRemoteDataStore
) {
    companion object{
        private var INSTANCE: BudgetDataStoreFactory? = null
        fun getInstance(
            budgetLocal: BudgetLocal,
            budgetRemote: BudgetRemote
        ) = INSTANCE
            ?: BudgetDataStoreFactory(
                BudgetLocalDataStore.getInstance(budgetLocal),
                BudgetRemoteDataStore.getInstance(budgetRemote)
            )
    }

    fun retrieveRemoteDataStore(): BudgetRemoteDataStore {
        return remoteDataStore
    }

    fun retrieveLocalDataStore(): BudgetLocalDataStore {
        return localDataStore
    }
}