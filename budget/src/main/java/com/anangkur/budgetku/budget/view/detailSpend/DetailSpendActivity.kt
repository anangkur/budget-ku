package com.anangkur.budgetku.budget.view.detailSpend

import android.content.Context
import android.content.Intent
import androidx.appcompat.widget.Toolbar
import com.anangkur.budgetku.base.BaseActivity
import com.anangkur.budgetku.budget.R
import com.anangkur.budgetku.budget.databinding.ActivityDetailSpendBinding

class DetailSpendActivity : BaseActivity<ActivityDetailSpendBinding, Nothing>() {

    companion object{
        fun startActivity(context: Context){
            context.startActivity(Intent(context, DetailSpendActivity::class.java))
        }
    }

    override val mViewModel: Nothing?
        get() = null
    override val mToolbar: Toolbar?
        get() = findViewById(R.id.toolbar)
    override val mTitleToolbar: String?
        get() = getString(R.string.toolbar_detail_spend)

    override fun setupView(): ActivityDetailSpendBinding {
        return ActivityDetailSpendBinding.inflate(layoutInflater)
    }

}