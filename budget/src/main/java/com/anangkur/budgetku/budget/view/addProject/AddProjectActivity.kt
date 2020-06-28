package com.anangkur.budgetku.budget.view.addProject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.anangkur.budgetku.base.BaseActivity
import com.anangkur.budgetku.budget.R
import com.anangkur.budgetku.budget.databinding.ActivityAddProjectBinding
import com.anangkur.budgetku.budget.utils.showAddCategoryDialog

class AddProjectActivity : BaseActivity<ActivityAddProjectBinding, Nothing>(),
    AddProjectActionListener {

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, AddProjectActivity::class.java))
        }
    }

    override val mViewModel: Nothing?
        get() = null
    override val mToolbar: Toolbar?
        get() = findViewById(R.id.toolbar)
    override val mTitleToolbar: String?
        get() = getString(R.string.toolbar_add_project)

    override fun setupView(): ActivityAddProjectBinding {
        return ActivityAddProjectBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mLayout.btnAddCategory.setOnClickListener { this.onClickAddCategory() }
    }

    override fun onClickAddCategory() {
        showAddCategoryDialog()
    }

}