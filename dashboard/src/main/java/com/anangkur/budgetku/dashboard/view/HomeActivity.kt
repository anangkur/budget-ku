package com.anangkur.budgetku.dashboard.view

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.anangkur.budgetku.base.BaseActivity
import com.anangkur.budgetku.dashboard.R
import com.anangkur.budgetku.dashboard.databinding.ActivityHomeBinding
import com.anangkur.budgetku.utils.Navigation.goToDetailProjectActivity
import com.anangkur.budgetku.R as appR

class HomeActivity : BaseActivity<ActivityHomeBinding, Nothing>() {

    override val mViewModel: Nothing?
        get() = null
    override val mToolbar: Toolbar?
        get() = findViewById(R.id.toolbar)
    override val mTitleToolbar: String?
        get() = getString(appR.string.app_name)

    override fun setupView(): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mLayout.btnAddProject.setOnClickListener { goToDetailProjectActivity() }
    }
}