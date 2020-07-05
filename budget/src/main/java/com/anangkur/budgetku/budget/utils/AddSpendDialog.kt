package com.anangkur.budgetku.budget.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

        mLayout.btnSave.setOnClickListener { addSpendDialogActionListener.onClickSave(this) }
        mLayout.etSpend.setOnClickListener { addSpendDialogActionListener.onClickSpend(this, defaultValue) }
        mLayout.cardSelectedCategory.setOnClickListener { addSpendDialogActionListener.onClickCategory() }
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

    fun setButtonSaveEnable() {
        mLayout.btnSave.apply {
            setColor(APP_R.color.colorPrimary)
            enable()
        }
    }

    fun setButtonSaveDisable() {
        mLayout.btnSave.apply {
            setColor(APP_R.color.gray)
            enable()
        }
    }
    
    private fun setCategoryNull() {
        mLayout.apply { 
            ivCategory.gone()
            tvCategory.text = context.getString(BUDGET_R.string.label_select_category)
        }
    }

    private fun setupTextWatcher() {
        mLayout.etSpend.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}