package com.anangkur.budgetku.budget.view.dialog.addCategory

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import com.anangkur.budgetku.base.BaseSpinnerListener
import com.anangkur.budgetku.budget.R
import com.anangkur.budgetku.budget.databinding.DialogAddCategoryBinding
import com.anangkur.budgetku.budget.model.CategoryUiModel
import com.anangkur.budgetku.currencyEditText.CurrencySymbols
import com.anangkur.budgetku.utils.gone
import com.anangkur.budgetku.utils.setImageUrl
import com.anangkur.budgetku.utils.visible


class AddCategoryDialog(
    context: Context,
    private val listener: AddCategoryDialogListener
): AlertDialog(context) {

    lateinit var mLayout: DialogAddCategoryBinding

    init {
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mLayout = DialogAddCategoryBinding.inflate(layoutInflater)
        setContentView(mLayout.root)

        clearInputtedValue()
        setupEditTextBudget()

        mLayout.btnSave.setOnClickListener { listener.onClickSave(this) }
        mLayout.btnCancel.setOnClickListener { listener.onClickCancel(this) }
        mLayout.cardSelectedCategory.setOnClickListener { listener.onClickCategory() }
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
            isCategoryNullOrEmpty -> {
                setButtonSaveDisable(context.getString(R.string.error_category_null_or_empty))
                false
            }
            isValueNullOrEmpty -> {
                setButtonSaveDisable(context.getString(R.string.error_value_null_or_empty))
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

    fun setCategory(data: CategoryUiModel) {
        mLayout.apply {
            ivCategory.visible()
            ivCategory.setImageUrl(data.image)
            tvCategory.text = data.title
        }
    }

    fun setCategoryNull() {
        mLayout.apply {
            ivCategory.gone()
            tvCategory.text = context.getString(R.string.label_select_category)
        }
    }

    private fun setValueNull() {
        mLayout.etBudget.setText("")
    }

    private fun setupEditTextBudget() {
        mLayout.etBudget.apply {
            setCurrency(CurrencySymbols.INDONESIA)
            setDecimals(false)
            setSeparator(".")
            setOnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard(v)
                    listener.onValueInputted(this.cleanDoubleValue)
                    true
                } else {
                    false
                }
            }
        }
    }

    fun clearInputtedValue() {
        setCategoryNull()
        setValueNull()
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}