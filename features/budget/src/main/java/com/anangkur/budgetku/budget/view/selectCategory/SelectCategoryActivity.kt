package com.anangkur.budgetku.budget.view.selectCategory

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.budgetku.base.BaseActivity
import com.anangkur.budgetku.budget.R
import com.anangkur.budgetku.budget.databinding.ActivitySelectCategoryBinding
import com.anangkur.budgetku.budget.mapper.CategoryMapper
import com.anangkur.budgetku.budget.model.CategoryUiModel
import com.anangkur.budgetku.presentation.features.budget.SelectCategoryViewModel
import com.anangkur.budgetku.utils.obtainViewModel
import com.anangkur.budgetku.utils.setupRecyclerViewLinear
import com.anangkur.budgetku.utils.showSnackbarShort

class SelectCategoryActivity : BaseActivity<ActivitySelectCategoryBinding, SelectCategoryViewModel>(),
    SelectCategoryActionListener {

    companion object {
        const val SELECT_CATEGORY_RES_CODE = 202
        const val SELECT_CATEGORY_REQ_CODE = 102
        const val EXTRA_CATEGORY = "category"
        fun startActivity(activity: AppCompatActivity) {
            activity.startActivityForResult(
                Intent(activity, SelectCategoryActivity::class.java), SELECT_CATEGORY_REQ_CODE
            )
        }
    }

    override val mViewModel: SelectCategoryViewModel
        get() = obtainViewModel(SelectCategoryViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = findViewById(R.id.toolbar)
    override val mTitleToolbar: String?
        get() = getString(R.string.toolbar_select_category)

    private lateinit var categoryAdapter: CategoryAdapter
    private val categoryMapper = CategoryMapper.getInstance()

    override fun setupView(): ActivitySelectCategoryBinding {
        return ActivitySelectCategoryBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupAdapter()
        observeViewModel()
        mViewModel.getCategory()
        mLayout.swipeCategory.setOnRefreshListener { mViewModel.getCategory() }
    }

    private fun observeViewModel() {
        mViewModel.apply {
            loadingGetCategory.observe(this@SelectCategoryActivity, Observer {
                mLayout.swipeCategory.isRefreshing = it
            })
            successGetCategory.observe(this@SelectCategoryActivity, Observer { list ->
                categoryAdapter.setRecyclerData(list.map { categoryMapper.mapToIntent(it) })
            })
            errorGetCategory.observe(this@SelectCategoryActivity, Observer {
                showSnackbarShort(it)
            })
        }
    }

    private fun setupAdapter() {
        categoryAdapter = CategoryAdapter(this)
        mLayout.recyclerCategory.apply {
            adapter = categoryAdapter
            setupRecyclerViewLinear(this@SelectCategoryActivity, RecyclerView.VERTICAL)
        }
    }

    override fun onClickCategory(data: CategoryUiModel) {
        setResult(SELECT_CATEGORY_RES_CODE, Intent().apply {
            putExtra(EXTRA_CATEGORY, data)
        })
        finish()
    }
}