package com.anangkur.budgetku.budget.view.detailProject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.budgetku.base.BaseActivity
import com.anangkur.budgetku.budget.R
import com.anangkur.budgetku.budget.databinding.ActivityDetailProjectBinding
import com.anangkur.budgetku.budget.mapper.BudgetMapper
import com.anangkur.budgetku.budget.mapper.CategoryMapper
import com.anangkur.budgetku.budget.mapper.SpendCategoryMapper
import com.anangkur.budgetku.budget.model.BudgetUiModel
import com.anangkur.budgetku.budget.model.CategoryUiModel
import com.anangkur.budgetku.budget.model.SpendCategoryUiModel
import com.anangkur.budgetku.budget.utils.AddSpendDialogActionListener
import com.anangkur.budgetku.budget.utils.AddSpendDialog
import com.anangkur.budgetku.budget.utils.showAddSpendDialog
import com.anangkur.budgetku.budget.view.detailSpend.DetailSpendActivity
import com.anangkur.budgetku.budget.view.selectCategory.SelectCategoryActivity
import com.anangkur.budgetku.calcDialog.AddSpendValueListener
import com.anangkur.budgetku.calcDialog.CalcDialog
import com.anangkur.budgetku.presentation.features.budget.DetailProjectViewModel
import com.anangkur.budgetku.utils.currencyFormatToRupiah
import com.anangkur.budgetku.utils.obtainViewModel
import com.anangkur.budgetku.utils.setupRecyclerViewLinear
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.math.BigDecimal

class DetailProjectActivity : BaseActivity<ActivityDetailProjectBinding, DetailProjectViewModel>(),
    DetailProjectActionListener, AddSpendDialogActionListener {

    override val mViewModel: DetailProjectViewModel
        get() = obtainViewModel(DetailProjectViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = findViewById(R.id.toolbar)
    override val mTitleToolbar: String?
        get() = getString(R.string.toolbar_monthly_budget)

    private lateinit var spendCategoryAdapter: SpendCategoryAdapter
    private var addSpendDialog: AddSpendDialog? = null

    private val budgetMapper = BudgetMapper.getInstance()
    private val spendCategoryMapper = SpendCategoryMapper.getInstance()
    private val categoryMapper = CategoryMapper.getInstance()

    override fun setupView(): ActivityDetailProjectBinding {
        return ActivityDetailProjectBinding.inflate(layoutInflater)
    }

    override fun onClickAddSpend() {
        addSpendDialog = showAddSpendDialog(addSpendDialog, this, mViewModel.spendValue)
    }

    override fun onClickSpendCategory(data: SpendCategoryUiModel) {
        DetailSpendActivity.startActivity(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBottomSheetHeight()
        setupSpendCategoryAdapter()
        observeViewModel()
        mViewModel.createDummyBudget()
        mViewModel.createDummyListSpendCategory()
        mLayout.btnAddSpend.setOnClickListener { this.onClickAddSpend() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SelectCategoryActivity.SELECT_CATEGORY_REQ_CODE
            && resultCode == SelectCategoryActivity.SELECT_CATEGORY_RES_CODE) {
            val category = data?.getParcelableExtra<CategoryUiModel>(SelectCategoryActivity.EXTRA_CATEGORY)
            category?.let {
                mViewModel.category = categoryMapper.mapFromIntent(it)
                addSpendDialog?.setCategory(it)
            }
        }
    }

    private fun observeViewModel() {
        mViewModel.apply {
            listSpendCategoryPublicObserver.observe(this@DetailProjectActivity, Observer {
                spendCategoryAdapter.setRecyclerData(it.map { item -> spendCategoryMapper.mapToIntent(item) })
            })
            budgetPublicObserver.observe(this@DetailProjectActivity, Observer {
                setupBudgetView(budgetMapper.mapToIntent(it))
            })
        }
    }

    private fun setupBudgetView(data: BudgetUiModel) {
        mLayout.apply {
            tvTotalBudget.text = data.totalBudget.toDouble().currencyFormatToRupiah()
            tvTotalSpend.text = data.totalSpend.toDouble().currencyFormatToRupiah()
            tvTotalRemaining.text = data.totalRemaining.toDouble().currencyFormatToRupiah()
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
            }
        })
        calcDialog.settings.isExpressionShown = true
        calcDialog.settings.initialValue = value.toBigDecimal()
        calcDialog.show(supportFragmentManager, "calc_dialog")
    }

    override fun onClickSave(dialog: AddSpendDialog) {
        addSpendDialog = null
        mViewModel.spendValue = 0.0
        dialog.dismiss()
    }

    override fun onClickCategory() {
        SelectCategoryActivity.startActivity(this)
    }
}