package com.anangkur.budgetku.budget.view.detailProject

import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.budgetku.base.BaseActivity
import com.anangkur.budgetku.budget.R
import com.anangkur.budgetku.budget.databinding.ActivityDetailProjectBinding
import com.anangkur.budgetku.budget.mapper.BudgetMapper
import com.anangkur.budgetku.budget.mapper.SpendCategoryMapper
import com.anangkur.budgetku.budget.model.BudgetUiModel
import com.anangkur.budgetku.budget.model.SpendCategoryUiModel
import com.anangkur.budgetku.budget.utils.showAddSpendDialog
import com.anangkur.budgetku.budget.view.detailSpend.DetailSpendActivity
import com.anangkur.budgetku.presentation.features.budget.DetailProjectViewModel
import com.anangkur.budgetku.utils.currencyFormatToRupiah
import com.anangkur.budgetku.utils.obtainViewModel
import com.anangkur.budgetku.utils.setupRecyclerViewLinear
import com.google.android.material.bottomsheet.BottomSheetBehavior

class DetailProjectActivity : BaseActivity<ActivityDetailProjectBinding, DetailProjectViewModel>(),
    DetailProjectActionListener {

    override val mViewModel: DetailProjectViewModel
        get() = obtainViewModel(DetailProjectViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = findViewById(R.id.toolbar)
    override val mTitleToolbar: String?
        get() = getString(R.string.toolbar_monthly_budget)

    private lateinit var spendCategoryAdapter: SpendCategoryAdapter

    private val budgetMapper = BudgetMapper.getInstance()
    private val spendCategoryMapper = SpendCategoryMapper.getInstance()

    override fun setupView(): ActivityDetailProjectBinding {
        return ActivityDetailProjectBinding.inflate(layoutInflater)
    }

    override fun onClickAddSpend() {
        showAddSpendDialog()
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

    private fun observeViewModel() {
        mViewModel.apply {
            listSpendCategoryPublicObserver.observe(this@DetailProjectActivity, Observer {
                spendCategoryAdapter.setRecyclerData(it.map { item -> spendCategoryMapper.mapToUiModel(item) })
            })
            budgetPublicObserver.observe(this@DetailProjectActivity, Observer {
                setupBudgetView(budgetMapper.mapToUiModel(it))
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
        mLayout.bottomSheetBudget.recyclerBudget.apply {
            setupRecyclerViewLinear(this@DetailProjectActivity, RecyclerView.VERTICAL)
            adapter = spendCategoryAdapter
        }
    }

    private fun setupBottomSheetHeight() {
        mLayout.root.viewTreeObserver.addOnGlobalLayoutListener {
            val constraintBudgetHeight = mLayout.constraintBudget.height
            val parentHeight = mLayout.root.height
            val toolbarHeight = mLayout.toolbar.height
            val bottomSheetBehaviour = BottomSheetBehavior.from(mLayout.bottomSheetBudget.bottomSheetBudget)
            bottomSheetBehaviour.peekHeight = parentHeight - (constraintBudgetHeight + toolbarHeight)
        }
    }
}