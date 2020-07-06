package com.anangkur.budgetku.budget.utils

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.anangkur.budgetku.budget.databinding.DialogAddCategoryBinding

fun Activity.showAddCategoryDialog() {
    val dialog = AlertDialog.Builder(this).create()
    val view = DialogAddCategoryBinding.inflate(LayoutInflater.from(this))

    view.apply {
        btnCancel.setOnClickListener {
            if (dialog.isShowing) dialog.dismiss()
        }
        btnSave.setOnClickListener {
            if (dialog.isShowing) dialog.dismiss()
        }
        cardSelectedCategory.setOnClickListener { spinnerCategory.performClick() }
    }

    dialog.apply {
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    dialog.setView(view.root)
    dialog.show()
}

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