package com.anangkur.budgetku.budget.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.anangkur.budgetku.budget.R as BUDGET_R
import com.anangkur.budgetku.R as APP_R
import com.anangkur.budgetku.budget.databinding.DialogAddSpendBinding
import com.anangkur.budgetku.budget.model.CategoryUiModel
import com.anangkur.budgetku.utils.*

class AddSpendDialog(
    context: Context,
    private val addSpendDialogActionListener: AddSpendDialogActionListener,
    private var defaultValue : Double = 0.0
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
        
        setSpendValue(defaultValue)
        setCategoryNull()

        mLayout.etSpend.setOnClickListener { addSpendDialogActionListener.onClickSpend(this, defaultValue) }
        mLayout.cardSelectedCategory.setOnClickListener { addSpendDialogActionListener.onClickCategory() }
        mLayout.btnSave.setOnClickListener { addSpendDialogActionListener.onClickSave(this@AddSpendDialog) }
    }

    fun setSpendValue(value: Double) {
        defaultValue = value
        mLayout.etSpend.setText(defaultValue.currencyFormatToRupiah())
    }

    fun setCategory(data: CategoryUiModel) {
        mLayout.apply {
            ivCategory.visible()
            ivCategory.setImageUrl(data.image)
            tvCategory.text = data.title
        }
    }

    private fun setButtonSaveEnable() {
        mLayout.tvError.gone()
    }

    private fun setButtonSaveDisable(errorMessage: String) {
        mLayout.tvError.apply {
            text = errorMessage
            visible()
        }
    }

    fun setupButtonSaveEnable(isCategoryNullOrEmpty: Boolean, isValueNullOrEmpty: Boolean): Boolean {
        return when {
            isValueNullOrEmpty -> {
                setButtonSaveDisable(context.getString(BUDGET_R.string.error_value_null_or_empty))
                false
            }
            isCategoryNullOrEmpty -> {
                setButtonSaveDisable(context.getString(BUDGET_R.string.error_category_null_or_empty))
                false
            }
            else -> {
                setButtonSaveEnable()
                true
            }
        }
    }

    fun setupButtonSaveLoading(isLoading: Boolean) {
        if (isLoading) {
            mLayout.btnSave.showProgress()
        } else {
            mLayout.btnSave.hideProgress()
        }
    }
    
    private fun setCategoryNull() {
        mLayout.apply { 
            ivCategory.gone()
            tvCategory.text = context.getString(BUDGET_R.string.label_select_category)
        }
    }
}