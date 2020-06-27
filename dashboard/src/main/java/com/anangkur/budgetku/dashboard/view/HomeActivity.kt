package com.anangkur.budgetku.dashboard.view

import androidx.appcompat.widget.Toolbar
import com.anangkur.budgetku.base.BaseActivity
import com.anangkur.budgetku.dashboard.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding, Nothing>() {

    override val mViewModel: Nothing?
        get() = null
    override val mToolbar: Toolbar?
        get() = null
    override val mTitleToolbar: String?
        get() = null

    override fun setupView(): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

}