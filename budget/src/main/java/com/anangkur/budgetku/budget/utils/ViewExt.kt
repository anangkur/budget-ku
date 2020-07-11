package com.anangkur.budgetku.budget.utils

import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.showAddSpendDialog(
    dialog: AddSpendDialog?,
    listener: AddSpendDialogActionListener,
    defaultValue: Double
): AddSpendDialog {
    return if (dialog == null) {
        val newDialog = AddSpendDialog(this, listener, defaultValue)
        newDialog.show()
        newDialog
    } else {
        dialog.show()
        dialog
    }
}