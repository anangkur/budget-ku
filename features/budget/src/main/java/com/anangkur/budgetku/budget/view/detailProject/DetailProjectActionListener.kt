package com.anangkur.budgetku.budget.view.detailProject

import com.anangkur.budgetku.budget.model.SpendCategoryUiModel

interface DetailProjectActionListener {
    fun onClickAddSpend()
    fun onClickSpendCategory(data: SpendCategoryUiModel)
}