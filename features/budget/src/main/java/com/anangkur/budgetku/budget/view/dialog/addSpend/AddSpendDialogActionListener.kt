package com.anangkur.budgetku.budget.view.dialog.addSpend

interface AddSpendDialogActionListener {
    fun onClickSpend(dialog: AddSpendDialog, value: Double)
    fun onClickSave(dialog: AddSpendDialog)
    fun onClickCancel(dialog: AddSpendDialog)
    fun onSelectCategory(dialog: AddSpendDialog, position: Int)
    fun onEditNote(note: String)
}