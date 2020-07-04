package com.anangkur.budgetku.budget.mapper

import com.anangkur.budgetku.budget.model.BudgetUiModel
import com.anangkur.budgetku.mapper.Mapper
import com.anangkur.budgetku.presentation.model.budget.BudgetView

class BudgetMapper : Mapper<BudgetUiModel, BudgetView> {

    companion object{
        private var INSTANCE: BudgetMapper? = null
        fun getInstance() = INSTANCE ?: BudgetMapper()
    }

    override fun mapFromIntent(type: BudgetUiModel): BudgetView {
        return BudgetView(
            totalBudget = type.totalBudget,
            totalRemaining = type.totalRemaining,
            totalSpend = type.totalSpend
        )
    }

    override fun mapToIntent(type: BudgetView): BudgetUiModel {
        return BudgetUiModel(
            totalBudget = type.totalBudget,
            totalRemaining = type.totalRemaining,
            totalSpend = type.totalSpend
        )
    }
}