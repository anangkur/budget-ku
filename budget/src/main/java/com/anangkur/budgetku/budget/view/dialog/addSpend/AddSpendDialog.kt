package com.anangkur.budgetku.budget.view.dialog.addSpend

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import com.anangkur.budgetku.base.BaseSpinnerListener
import com.anangkur.budgetku.budget.R as BUDGET_R
import com.anangkur.budgetku.budget.databinding.DialogAddSpendBinding
import com.anangkur.budgetku.budget.model.CategoryUiModel
import com.anangkur.budgetku.utils.*

class AddSpendDialog(
    context: Context,
    private val itemAtZero: String?,
    private val data: List<String>,
    private val listener: AddSpendDialogActionListener,
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
        setupSpinner(data, itemAtZero)

        mLayout.etSpend.setOnClickListener {
            listener.onClickSpend(this, defaultValue)
        }
        mLayout.btnSave.setOnClickListener {
            listener.onClickSave(this@AddSpendDialog)
        }
        mLayout.btnCancel.setOnClickListener {
            listener.onClickCancel(this@AddSpendDialog)
        }
        mLayout.cardSelectedCategory.setOnClickListener {
            mLayout.spinnerCategory.performClick()
        }
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
    
    fun setCategoryNull() {
        mLayout.apply { 
            ivCategory.gone()
            tvCategory.text = context.getString(BUDGET_R.string.label_select_category)
        }
    }

    private fun setupSpinner(data: List<String>, itemAtZero: String? = null, selectedCategoryPosition: Int = 0) {
        mLayout.spinnerCategory.apply {
            setSelection(selectedCategoryPosition)
            setupSpinner(data, itemAtZero, object:
                BaseSpinnerListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if (itemAtZero != null) {
                        listener.onSelectCategory(this@AddSpendDialog, position-1)
                    } else {
                        listener.onSelectCategory(this@AddSpendDialog, position)
                    }
                }
            })
        }
    }
}