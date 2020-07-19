package com.anangkur.budgetku.budget.view.addProject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.budgetku.base.BaseActivity
import com.anangkur.budgetku.budget.R
import com.anangkur.budgetku.R as appR
import com.anangkur.budgetku.budget.databinding.ActivityAddProjectBinding
import com.anangkur.budgetku.budget.mapper.CategoryMapper
import com.anangkur.budgetku.budget.model.CategoryUiModel
import com.anangkur.budgetku.budget.view.dialog.addCategory.AddCategoryDialog
import com.anangkur.budgetku.budget.view.dialog.addCategory.AddCategoryDialogListener
import com.anangkur.budgetku.budget.view.selectCategory.SelectCategoryActivity
import com.anangkur.budgetku.mapper.CategoryProjectMapper
import com.anangkur.budgetku.model.CategoryProjectIntent
import com.anangkur.budgetku.presentation.features.budget.AddProjectViewModel
import com.anangkur.budgetku.utils.*
import com.annimon.stream.Stream
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.builders.DatePickerBuilder
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener
import kotlinx.android.synthetic.main.activity_add_project.*
import java.text.SimpleDateFormat
import java.util.*

class AddProjectActivity : BaseActivity<ActivityAddProjectBinding, AddProjectViewModel>(),
    AddProjectActionListener {

    companion object {
        const val DATE_ENGLISH_YYYY_MM_DD = "yyyy-M-d"
        const val TYPE_START_DATE = 0
        const val TYPE_END_DATE = 1
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
    private val categoryProjectMapper = CategoryProjectMapper.getInstance()

    private lateinit var categoryProjectAdapter: CategoryProjectAdapter

    override fun setupView(): ActivityAddProjectBinding {
        return ActivityAddProjectBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupAddCategoryDialog()
        setupCategoryProjectAdapter()
        setupTextWatcher()
        observeViewModel()
        mLayout.btnAddCategory.setOnClickListener { this.onClickAddCategory() }
        mLayout.btnSave.setOnClickListener { this.onClickSave() }
        mLayout.etStartDate.setOnClickListener { this.onClickDate(TYPE_START_DATE) }
        mLayout.etEndDate.setOnClickListener { this.onClickDate(TYPE_END_DATE) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SelectCategoryActivity.SELECT_CATEGORY_REQ_CODE
            && resultCode == SelectCategoryActivity.SELECT_CATEGORY_RES_CODE) {
            val category = data?.getParcelableExtra<CategoryUiModel>(SelectCategoryActivity.EXTRA_CATEGORY)
            category?.let {
                mViewModel.categorySelectedValue = categoryMapper.mapFromIntent(it)
                addCategoryDialog?.setupButtonSaveEnable(
                    isValueNullOrEmpty = mViewModel.budgetValue <= 0.0,
                    isCategoryNullOrEmpty = mViewModel.categorySelectedValue == null
                )
                addCategoryDialog?.setCategory(it)
            }
        }
    }

    private fun setupAddCategoryDialog() {
        if (addCategoryDialog == null) {
            addCategoryDialog =
                AddCategoryDialog(
                    context = this,
                    listener = object :
                        AddCategoryDialogListener {
                        override fun onClickCancel(dialog: AddCategoryDialog) {
                            dialog.dismiss()
                        }

                        override fun onClickSave(dialog: AddCategoryDialog) {
                            if (dialog.setupButtonSaveEnable(
                                    mViewModel.categorySelectedValue == null,
                                    mViewModel.budgetValue <= 0
                                )
                            ) {
                                mViewModel.addCategoryProject(
                                    categoryProjectMapper.mapFromIntent(
                                        CategoryProjectIntent(
                                            id = DateUtils.getCreatedAt(),
                                            title = mViewModel.categorySelectedValue?.title ?: "",
                                            value = mViewModel.budgetValue,
                                            image = mViewModel.categorySelectedValue?.image ?: "",
                                            spend = 0.0,
                                            remaining = 0.0
                                        )
                                    )
                                )
                                mViewModel.categorySelectedValue = null
                                mViewModel.budgetValue = 0.0
                                dialog.clearInputtedValue()
                                dialog.dismiss()
                                mLayout.tvErrorCategory.gone()
                            }
                        }

                        override fun onClickCategory() {
                            SelectCategoryActivity.startActivity(this@AddProjectActivity)
                        }

                        override fun onValueInputted(value: Double) {
                            mViewModel.budgetValue = value
                        }
                    }
                )
        }
    }

    override fun onClickAddCategory() {
        addCategoryDialog?.show()
        addCategoryDialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        addCategoryDialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    override fun onClickSave() {
        when {
            mLayout.etTitle.text.isNullOrEmpty() -> {
                mLayout.tilTitle.isErrorEnabled = true
                mLayout.tilTitle.error = getString(R.string.error_projecttitle_null_or_empty)
            }
            mLayout.etStartDate.text.isNullOrEmpty() -> {
                mLayout.tilStartDate.isErrorEnabled = true
                mLayout.tilStartDate.error = getString(R.string.error_startdate_null_or_empty)
            }
            mLayout.etEndDate.text.isNullOrEmpty() -> {
                mLayout.tilEndDate.isErrorEnabled = true
                mLayout.tilEndDate.error = getString(R.string.error_enddate_null_or_empty)
            }
            mViewModel.startDate?.after(mViewModel.endDate) ?: false -> {
                mLayout.tilStartDate.isErrorEnabled = true
                mLayout.tilStartDate.error = getString(R.string.error_startdate_after_enddate)
            }
            mViewModel.getCategoryProject().isEmpty() -> {
                mLayout.tilStartDate.isErrorEnabled = false
                mLayout.tvErrorCategory.visible()
                mLayout.tvErrorCategory.text = getString(R.string.error_category_null_or_empty)
            }
            else -> {
                mViewModel.createProject(
                    title = mLayout.etTitle.text.toString(),
                    category = mViewModel.getCategoryProject(),
                    endDate = mLayout.etEndDate.text.toString(),
                    startDate = mLayout.etStartDate.text.toString()
                )
            }
        }
    }

    override fun onClickDate(type: Int) {
        val minimumDate: Calendar?
        if (type == TYPE_START_DATE) {
            minimumDate = Calendar.getInstance()
            minimumDate.add(Calendar.DAY_OF_MONTH, -1)
        } else {
            if (mViewModel.startDate != null) {
                minimumDate = mViewModel.startDate
                minimumDate?.add(Calendar.DAY_OF_MONTH, 1)
            } else {
                til_start_date.isErrorEnabled = true
                til_start_date.error = getString(R.string.error_startdate_not_selected)
                return
            }
        }
        val builder = DatePickerBuilder(this, OnSelectDateListener {
            Stream.of(it).forEach { calendar ->
                val dateShow = SimpleDateFormat(DATE_ENGLISH_YYYY_MM_DD, Locale.getDefault()).format(calendar.time)
                if (type == TYPE_START_DATE) {
                    mLayout.etStartDate.setText(dateShow)
                    mLayout.tilStartDate.isErrorEnabled = false
                    mViewModel.startDate = calendar
                } else {
                    mLayout.etEndDate.setText(dateShow)
                    mLayout.tilEndDate.isErrorEnabled = false
                    mViewModel.endDate = calendar
                }
            }
        })
            .pickerType(CalendarView.ONE_DAY_PICKER)
            .minimumDate(minimumDate)
            .headerColor(appR.color.white)
            .headerLabelColor(appR.color.colorPrimary)
            .selectionColor(appR.color.colorPrimary)
            .todayLabelColor(appR.color.colorPrimary)
            .dialogButtonsColor(appR.color.colorPrimary)

        val datePicker = builder.build()
        datePicker.show()
    }

    override fun onClickDeleteCategory(position: Int) {
        mViewModel.deleteCategoryProject(position)
    }

    private fun observeViewModel() {
        mViewModel.apply {
            listCategoryProjectPublicObserver.observe(this@AddProjectActivity, Observer {
                categoryProjectAdapter.setRecyclerData(it.map { item -> categoryProjectMapper.mapToIntent(item) })
            })
            loadingCreateProject.observe(this@AddProjectActivity, Observer {
                if (it)
                    mLayout.btnSave.showProgress()
                else
                    mLayout.btnSave.hideProgress()
            })
            successCreateProject.observe(this@AddProjectActivity, Observer {
                showToastShort(getString(R.string.message_success_create_project))
                finish()
            })
            errorCreateProject.observe(this@AddProjectActivity, Observer {
                showSnackbarShort(it)
            })
        }
    }

    private fun setupCategoryProjectAdapter() {
        categoryProjectAdapter = CategoryProjectAdapter(this)
        mLayout.recyclerCategory.apply {
            adapter = categoryProjectAdapter
            setupRecyclerViewLinear(this@AddProjectActivity, RecyclerView.VERTICAL)
        }
    }

    private fun setupTextWatcher() {
        mLayout.etTitle.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty() && mLayout.tilTitle.isErrorEnabled) {
                    mLayout.tilTitle.isErrorEnabled = false
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}