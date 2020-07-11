package com.anangkur.budgetku.budget.view.dialog.addCategory

import com.anangkur.budgetku.budget.view.dialog.addCategory.AddCategoryDialog

interface AddCategoryDialogListener {
    fun onClickCancel(dialog: AddCategoryDialog)
    fun onClickSave(dialog: AddCategoryDialog)
    fun onValueInputted(value: Double)
    fun onClickCategory()
}