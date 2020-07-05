package com.anangkur.budgetku.budget.utils

interface AddSpendDialogActionListener {
    fun onClickSpend(dialog: AddSpendDialog, value: Double)
    fun onClickSave(dialog: AddSpendDialog)
    fun onClickCategory()
}