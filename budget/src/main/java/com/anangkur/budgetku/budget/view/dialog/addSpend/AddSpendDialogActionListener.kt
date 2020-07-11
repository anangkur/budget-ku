package com.anangkur.budgetku.budget.view.dialog.addSpend

import com.anangkur.budgetku.budget.view.dialog.addCategory.AddCategoryDialog
import com.anangkur.budgetku.budget.view.dialog.addSpend.AddSpendDialog

interface AddSpendDialogActionListener {
    fun onClickSpend(dialog: AddSpendDialog, value: Double)
    fun onClickSave(dialog: AddSpendDialog)
    fun onClickCancel(dialog: AddSpendDialog)
    fun onSelectCategory(dialog: AddSpendDialog, position: Int)
}