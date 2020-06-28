package com.anangkur.budgetku.budget.view.detailProject

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.anangkur.budgetku.base.BaseActivity
import com.anangkur.budgetku.budget.R
import com.anangkur.budgetku.budget.databinding.ActivityDetailProjectBinding
import com.anangkur.budgetku.budget.utils.showAddSpendDialog
import com.anangkur.budgetku.budget.view.detailSpend.DetailSpendActivity
import com.anangkur.budgetku.utils.currencyFormatToRupiah
import java.math.BigDecimal

class DetailProjectActivity : BaseActivity<ActivityDetailProjectBinding, Nothing>(),
    DetailProjectActionListener {

    override val mViewModel: Nothing?
        get() = null
    override val mToolbar: Toolbar?
        get() = findViewById(R.id.toolbar)
    override val mTitleToolbar: String?
        get() = getString(R.string.toolbar_monthly_budget)

    override fun setupView(): ActivityDetailProjectBinding {
        return ActivityDetailProjectBinding.inflate(layoutInflater)
    }

    override fun onClickAddSpend() {
        showAddSpendDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mLayout.btnAddSpend.setOnClickListener { this.onClickAddSpend() }
    }
}