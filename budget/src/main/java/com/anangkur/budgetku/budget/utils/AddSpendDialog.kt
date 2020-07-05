package com.anangkur.budgetku.budget.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.anangkur.budgetku.budget.databinding.DialogAddSpendBinding
import com.anangkur.budgetku.budget.model.CategoryUiModel
import com.anangkur.budgetku.utils.setImageUrl

class AddSpendDialog(
    context: Context,
    private val addSpendDialogActionListener: AddSpendDialogActionListener
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

        mLayout.btnSave.setOnClickListener { addSpendDialogActionListener.onClickSave(this) }
        mLayout.etSpend.setOnClickListener { addSpendDialogActionListener.onClickSpend(this) }
        mLayout.cardSelectedCategory.setOnClickListener { addSpendDialogActionListener.onClickCategory() }
    }

    fun setSpendValue(value: String) {
        mLayout.etSpend.setText(value)
    }

    fun setCategory(data: CategoryUiModel) {
        mLayout.apply {
            ivCategory.setImageUrl(data.image)
            tvCategory.text = data.title
        }
    }
}