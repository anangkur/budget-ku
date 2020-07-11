package com.anangkur.budgetku.budget.view.addProject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.anangkur.budgetku.base.BaseActivity
import com.anangkur.budgetku.budget.R
import com.anangkur.budgetku.budget.databinding.ActivityAddProjectBinding
import com.anangkur.budgetku.budget.mapper.CategoryMapper
import com.anangkur.budgetku.budget.utils.AddCategoryDialog
import com.anangkur.budgetku.budget.utils.AddCategoryDialogListener
import com.anangkur.budgetku.presentation.features.budget.AddProjectViewModel
import com.anangkur.budgetku.utils.hideSoftKeyboard
import com.anangkur.budgetku.utils.obtainViewModel

class AddProjectActivity : BaseActivity<ActivityAddProjectBinding, AddProjectViewModel>(),
    AddProjectActionListener {

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, AddProjectActivity::class.java))
        }
    }

    override val mViewModel: AddProjectViewModel
        get() = obtainViewModel(AddProjectViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = findViewById(R.id.toolbar)
    override val mTitleToolbar: String?
        get() = getString(R.string.toolbar_add_project)

    private var addCategoryDialog: AddCategoryDialog? = null
    private val categoryMapper = CategoryMapper.getInstance()

    override fun setupView(): ActivityAddProjectBinding {
        return ActivityAddProjectBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeViewModel()
        mViewModel.createDummyListCategory()
        mLayout.btnAddCategory.setOnClickListener { this.onClickAddCategory() }
    }

    private fun setupAddCategoryDialog(data: List<String>) {
        addCategoryDialog = AddCategoryDialog(
            context = this,
            data = data,
            listener = object: AddCategoryDialogListener{
            override fun onClickCancel(dialog: AddCategoryDialog) {
                dialog.dismiss()
            }

            override fun onClickSave(dialog: AddCategoryDialog) {
                if (dialog.setupButtonSaveEnable(
                        mViewModel.categorySelectedValue == null,
                        mViewModel.budgetValue <= 0
                    )
                ) {
                    mViewModel.categorySelectedValue = null
                    mViewModel.categorySelectedPosition = 0
                    mViewModel.budgetValue = 0.0
                    dialog.clearInputtedValue()
                    dialog.dismiss()
                }
            }

            override fun onSelectCategory(dialog: AddCategoryDialog, position: Int) {
                mViewModel.apply {
                    categorySelectedPosition = position
                    if (position > 0) {
                        categorySelectedValue = listCategory[categorySelectedPosition]
                        dialog.setCategory(categoryMapper.mapToIntent(categorySelectedValue!!))
                    } else {
                        categorySelectedValue = null
                        dialog.setCategoryNull()
                    }
                }
            }

            override fun onValueInputted(value: Double) {
                mViewModel.budgetValue = value
            }
        })
    }

    override fun onClickAddCategory() {
        addCategoryDialog?.show()
        addCategoryDialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        addCategoryDialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    private fun observeViewModel() {
        mViewModel.apply {
            listCategoryPublicObserver.observe(this@AddProjectActivity, Observer {
                setupAddCategoryDialog(it.map { listCategory -> listCategory.title })
            })
        }
    }

}