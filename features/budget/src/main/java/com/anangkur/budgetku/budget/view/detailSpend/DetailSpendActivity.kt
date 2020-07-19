package com.anangkur.budgetku.budget.view.detailSpend

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.budgetku.base.BaseActivity
import com.anangkur.budgetku.budget.R
import com.anangkur.budgetku.budget.databinding.ActivityDetailSpendBinding
import com.anangkur.budgetku.budget.mapper.SpendMapper
import com.anangkur.budgetku.presentation.features.budget.DetailSpendViewModel
import com.anangkur.budgetku.utils.obtainViewModel
import com.anangkur.budgetku.utils.setupRecyclerViewLinear
import com.anangkur.budgetku.utils.showSnackbarShort

class DetailSpendActivity : BaseActivity<ActivityDetailSpendBinding, DetailSpendViewModel>() {

    companion object{
        private const val EXTRA_ID_PROJECT = "extra-id-project"
        private const val EXTRA_ID_CATEGORY = "extra-id-category"
        fun startActivity(context: Context, idProject: String, idCategory: String?){
            context.startActivity(Intent(context, DetailSpendActivity::class.java).apply {
                putExtra(EXTRA_ID_PROJECT, idProject)
                putExtra(EXTRA_ID_CATEGORY, idCategory)
            })
        }
    }

    override val mViewModel: DetailSpendViewModel
        get() = obtainViewModel(DetailSpendViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = findViewById(R.id.toolbar)
    override val mTitleToolbar: String?
        get() = getString(R.string.toolbar_detail_spend)

    private lateinit var spendAdapter: SpendAdapter

    private val spendMapper = SpendMapper.getInstance()

    override fun setupView(): ActivityDetailSpendBinding {
        return ActivityDetailSpendBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupAdapter()
        observeViewModel()
        getIntentData()
        mViewModel.getListSpend(mViewModel.idProject, mViewModel.idCategory)
        mLayout.swipeSpend.setOnRefreshListener {
            mViewModel.getListSpend(mViewModel.idProject, mViewModel.idCategory)
        }
    }

    private fun getIntentData() {
        mViewModel.idProject = intent.getStringExtra(EXTRA_ID_PROJECT) ?: ""
        mViewModel.idCategory = intent.getStringExtra(EXTRA_ID_CATEGORY)
    }

    private fun observeViewModel() {
        mViewModel.apply {
            loadingGetListSpend.observe(this@DetailSpendActivity, Observer {
                mLayout.swipeSpend.isRefreshing = it
            })
            successGetListSpend.observe(this@DetailSpendActivity, Observer {
                spendAdapter.setRecyclerData(it.map { item -> spendMapper.mapToIntent(item) })
            })
            errorGetListSpend.observe(this@DetailSpendActivity, Observer {
                showSnackbarShort(it)
            })
        }
    }

    private fun setupAdapter() {
        spendAdapter = SpendAdapter()
        mLayout.recyclerSpend.apply {
            adapter = spendAdapter
            setupRecyclerViewLinear(this@DetailSpendActivity, RecyclerView.VERTICAL)
        }
    }

}