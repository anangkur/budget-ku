package com.anangkur.budgetku.budget.utils

import androidx.appcompat.app.AlertDialog

interface AddCategoryDialogListener {
    fun onClickCancel(dialog: AddCategoryDialog)
    fun onClickSave(dialog: AddCategoryDialog)
    fun onSelectCategory(dialog: AddCategoryDialog, position: Int)
    fun onValueInputted(value: Double)
}