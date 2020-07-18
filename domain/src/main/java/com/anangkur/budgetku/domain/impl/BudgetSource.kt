package com.anangkur.budgetku.domain.impl

import com.anangkur.budgetku.domain.repository.BudgetRepository

class BudgetSource(private val budgetRepository: BudgetRepository) {

    companion object{
        private var INSTANCE: BudgetSource? = null
        fun getInstance(budgetRepository: BudgetRepository) = INSTANCE
            ?: BudgetSource(budgetRepository)
    }

}