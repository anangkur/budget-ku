package com.anangkur.budgetku.budget.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.anangkur.budgetku.budget.databinding.DialogAddSpendBinding

class AddSpendDialog(
    context: Context,
    private val addSpendActionListener: AddSpendActionListener
): AlertDialog(context) {

    lateinit var mLayout: DialogAddSpendBinding

    init {
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mLayout = DialogAddSpendBinding.inflate(layoutInflater)
        setContentView(mLayout.root)

        mLayout.btnSave.setOnClickListener { addSpendActionListener.onClickSave(this) }
        mLayout.etSpend.setOnClickListener { addSpendActionListener.onClickSpend(this) }
    }

    fun setSpendValue(value: String) {
        mLayout.etSpend.setText(value)
    }
}