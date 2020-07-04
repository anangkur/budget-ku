package com.anangkur.budgetku.budget.mapper

import com.anangkur.budgetku.budget.model.BudgetUiModel
import com.anangkur.budgetku.presentation.model.BudgetView

class BudgetMapper : Mapper<BudgetUiModel, BudgetView> {

    companion object{
        private var INSTANCE: BudgetMapper? = null
        fun getInstance() = INSTANCE ?: BudgetMapper()
    }

    override fun mapFromUiModel(data: BudgetUiModel): BudgetView {
        return BudgetView(
            totalBudget = data.totalBudget,
            totalRemaining = data.totalRemaining,
            totalSpend = data.totalSpend
        )
    }

    override fun mapToUiModel(data: BudgetView): BudgetUiModel {
        return BudgetUiModel(
            totalBudget = data.totalBudget,
            totalRemaining = data.totalRemaining,
            totalSpend = data.totalSpend
        )
    }
}