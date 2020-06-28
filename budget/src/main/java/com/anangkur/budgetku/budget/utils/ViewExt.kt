package com.anangkur.budgetku.budget.utils

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.anangkur.budgetku.calcDialog.CalcDialog
import com.anangkur.budgetku.budget.databinding.DialogAddCategoryBinding
import com.anangkur.budgetku.calcDialog.AddSpendValueListener
import com.anangkur.budgetku.utils.currencyFormatToRupiah
import java.math.BigDecimal

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

fun AppCompatActivity.showAddSpendDialog() {
    val dialog = AddSpendDialog(this, object: AddSpendActionListener{
        override fun onClickSpend(dialog: AddSpendDialog) {
            val calcDialog = CalcDialog(object : AddSpendValueListener {
                override fun setValue(value: BigDecimal) {
                    val stringSpend = value.toDouble().currencyFormatToRupiah()
                    dialog.setSpendValue(stringSpend)
                }
            })
            calcDialog.settings.isExpressionShown = true
            calcDialog.show(supportFragmentManager, "calc_dialog")
        }

        override fun onClickSave(dialog: AddSpendDialog) {
            dialog.dismiss()
        }

    })

    dialog.show()
}