package com.anangkur.budgetku.budget.view

import androidx.appcompat.widget.Toolbar
import com.anangkur.budgetku.base.BaseActivity
import com.anangkur.budgetku.budget.R
import com.anangkur.budgetku.budget.databinding.ActivityDetailProjectBinding
import com.anangkur.budgetku.R as appR

class DetailProjectActivity : BaseActivity<ActivityDetailProjectBinding, Nothing>() {

    override val mViewModel: Nothing?
        get() = null
    override val mToolbar: Toolbar?
        get() = findViewById(appR.id.toolbar)
    override val mTitleToolbar: String?
        get() = getString(R.string.toolbar_monthly_budget)

    override fun setupView(): ActivityDetailProjectBinding {
        return ActivityDetailProjectBinding.inflate(layoutInflater)
    }
}