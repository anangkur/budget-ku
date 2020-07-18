package com.anangkur.budgetku.budget.view.detailProject

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.budgetku.base.BaseActivity
import com.anangkur.budgetku.budget.R
import com.anangkur.budgetku.budget.databinding.ActivityDetailProjectBinding
import com.anangkur.budgetku.budget.view.dialog.addSpend.AddSpendDialogActionListener
import com.anangkur.budgetku.budget.view.dialog.addSpend.AddSpendDialog
import com.anangkur.budgetku.budget.view.detailSpend.DetailSpendActivity
import com.anangkur.budgetku.calcDialog.AddSpendValueListener
import com.anangkur.budgetku.calcDialog.CalcDialog
import com.anangkur.budgetku.mapper.CategoryProjectMapper
import com.anangkur.budgetku.mapper.ProjectMapper
import com.anangkur.budgetku.model.CategoryProjectIntent
import com.anangkur.budgetku.model.ProjectIntent
import com.anangkur.budgetku.presentation.features.budget.DetailProjectViewModel
import com.anangkur.budgetku.utils.currencyFormatToRupiah
import com.anangkur.budgetku.utils.obtainViewModel
import com.anangkur.budgetku.utils.setupRecyclerViewLinear
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.math.BigDecimal

class DetailProjectActivity : BaseActivity<ActivityDetailProjectBinding, DetailProjectViewModel>(),
    DetailProjectActionListener,
    AddSpendDialogActionListener {

    companion object {
        const val EXTRA_DETAIL_PROJECT = "extra-detail-project"
    }

    override val mViewModel: DetailProjectViewModel
        get() = obtainViewModel(DetailProjectViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = findViewById(R.id.toolbar)
    override val mTitleToolbar: String?
        get() = getString(R.string.toolbar_monthly_budget)

    private lateinit var spendCategoryAdapter: SpendCategoryAdapter
    private var addSpendDialog: AddSpendDialog? = null

    private val projectMapper = ProjectMapper.getInstance()
    private val categoryProjectMapper = CategoryProjectMapper.getInstance()

    override fun setupView(): ActivityDetailProjectBinding {
        return ActivityDetailProjectBinding.inflate(layoutInflater)
    }

    override fun onClickAddSpend() {
        addSpendDialog?.show()
    }

    override fun onClickSpendCategory(data: CategoryProjectIntent) {
        DetailSpendActivity.startActivity(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBottomSheetHeight()
        setupSpendCategoryAdapter()
        observeViewModel()
        getDetailProject()
        mLayout.btnAddSpend.setOnClickListener { this.onClickAddSpend() }
    }

    private fun observeViewModel() {
        mViewModel.apply {
            budgetPublicObserver.observe(this@DetailProjectActivity, Observer {
                setupBudgetView(projectMapper.mapToIntent(it))
            })
            listCategoryPublicObserver.observe(this@DetailProjectActivity, Observer { list ->
                spendCategoryAdapter.setRecyclerData(list.map { categoryProjectMapper.mapToIntent(it) })
                setupAddSpendDialog(list.map { item -> item.title })
            })
        }
    }

    private fun getDetailProject() {
        val detailProject = intent.getParcelableExtra<ProjectIntent>(EXTRA_DETAIL_PROJECT)
        detailProject?.let { mViewModel.setProjectDetail(projectMapper.mapFromIntent(detailProject)) }
    }

    private fun setupBudgetView(data: ProjectIntent) {
        mLayout.apply {
            tvTotalBudget.text = data.totalBudget.currencyFormatToRupiah()
            tvTotalSpend.text = data.totalSpend.currencyFormatToRupiah()
            tvTotalRemaining.text = data.totalRemaining.currencyFormatToRupiah()
        }
    }

    private fun setupSpendCategoryAdapter() {
        spendCategoryAdapter = SpendCategoryAdapter(this)
        mLayout.recyclerBudget.apply {
            setupRecyclerViewLinear(this@DetailProjectActivity, RecyclerView.VERTICAL)
            adapter = spendCategoryAdapter
        }
    }

    private fun setupBottomSheetHeight() {
        mLayout.root.viewTreeObserver.addOnGlobalLayoutListener {
            val constraintBudgetHeight = mLayout.constraintBudget.height
            val parentHeight = mLayout.root.height
            val toolbarHeight = mLayout.toolbar.height
            val bottomSheetBehaviour = BottomSheetBehavior.from(mLayout.bottomSheetBudget)
            bottomSheetBehaviour.peekHeight = parentHeight - (constraintBudgetHeight + toolbarHeight)
        }
    }

    override fun onClickSpend(dialog: AddSpendDialog, value: Double) {
        val calcDialog = CalcDialog(object : AddSpendValueListener {
            override fun setValue(value: BigDecimal) {
                mViewModel.spendValue = value.toDouble()
                dialog.setSpendValue(mViewModel.spendValue)
                dialog.setupButtonSaveEnable(
                    isValueNullOrEmpty = mViewModel.spendValue <= 0.0,
                    isCategoryNullOrEmpty = mViewModel.categorySelectedValue == null
                )
            }
        })
        calcDialog.settings.isExpressionShown = true
        calcDialog.settings.initialValue = value.toBigDecimal()
        calcDialog.show(supportFragmentManager, "calc_dialog")
    }

    override fun onClickSave(dialog: AddSpendDialog) {
        if (dialog.setupButtonSaveEnable(
                isValueNullOrEmpty = mViewModel.spendValue <= 0.0,
                isCategoryNullOrEmpty = mViewModel.categorySelectedValue == null
            )
        ){
            addSpendDialog = null
            mViewModel.spendValue = 0.0
            dialog.dismiss()
        }
    }

    override fun onClickCancel(dialog: AddSpendDialog) {
        dialog.dismiss()
    }

    override fun onSelectCategory(dialog: AddSpendDialog, position: Int) {
        mViewModel.apply {
            categorySelectedPosition = position
            if (position >= 0) {
                categorySelectedValue = listCategory[categorySelectedPosition]
                dialog.setCategory(categoryProjectMapper.mapToIntent(categorySelectedValue!!))
            } else {
                categorySelectedValue = null
                dialog.setCategoryNull()
            }
        }
    }

    private fun setupAddSpendDialog(data: List<String>) {
        addSpendDialog = AddSpendDialog(this, getString(R.string.label_select_category), data,this)
    }
}