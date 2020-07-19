package com.anangkur.budgetku.dashboard.view

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.budgetku.base.BaseActivity
import com.anangkur.budgetku.base.BaseErrorView
import com.anangkur.budgetku.dashboard.R
import com.anangkur.budgetku.dashboard.databinding.ActivityHomeBinding
import com.anangkur.budgetku.mapper.ProjectMapper
import com.anangkur.budgetku.dashboard.mapper.UserMapper
import com.anangkur.budgetku.dashboard.model.UserIntent
import com.anangkur.budgetku.model.ProjectIntent
import com.anangkur.budgetku.presentation.features.dashboard.HomeViewModel
import com.anangkur.budgetku.utils.*
import com.anangkur.budgetku.utils.Navigation.goToAddProjectActivity
import com.anangkur.budgetku.utils.Navigation.goToDetailProjectActivity
import com.anangkur.budgetku.utils.Navigation.goToProfileActivity
import java.text.SimpleDateFormat
import java.util.*
import com.anangkur.budgetku.R as appR

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeActivityActionListener {

    companion object {
        private const val DATE_FORMAT_HOME = "EEEE, MMM dd yyyy"
    }

    override val mViewModel: HomeViewModel
        get() = obtainViewModel(HomeViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = findViewById(R.id.toolbar)
    override val mTitleToolbar: String?
        get() = getString(appR.string.app_name)

    private lateinit var projectAdapter: ProjectAdapter

    private val projectMapper = ProjectMapper.getInstance()
    private val userMapper = UserMapper.getInstance()

    override fun setupView(): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupDateView(Calendar.getInstance().time)
        setupRecyclerProject()
        setupClickListener()
        observeViewModel()
        mViewModel.createDummyUser()
    }

    override fun onResume() {
        super.onResume()
        mViewModel.getProject()
    }

    override fun onClickAddProject() {
        goToAddProjectActivity()
    }

    override fun onClickEditProfile() {
        goToProfileActivity()
    }

    override fun onClickItem(data: ProjectIntent) {
        goToDetailProjectActivity(data)
    }

    private fun observeViewModel() {
        mViewModel.apply {
            loadingGetProject.observe(this@HomeActivity, Observer {
                mLayout.evProject.visible()
                if (it) {
                    mLayout.evProject.showProgress()
                } else {
                    mLayout.evProject.endProgress()
                }
            })
            successGetProject.observe(this@HomeActivity, Observer { list ->
                if (list.isNullOrEmpty()) {
                    mLayout.recyclerProject.gone()
                    mLayout.evProject.visible()
                    mLayout.evProject.showError(
                        errorMessage = getString(R.string.error_project_empty),
                        errorType = BaseErrorView.ERROR_NULL_DATA
                    )
                } else {
                    mLayout.recyclerProject.visible()
                    mLayout.evProject.gone()
                    projectAdapter.setRecyclerData(list.map { projectMapper.mapToIntent(it) })
                }
            })
            errorGetProject.observe(this@HomeActivity, Observer {
                mLayout.evProject.visible()
                mLayout.evProject.showError(
                    errorMessage = it,
                    buttonErrorString = getString(R.string.btn_retry),
                    errorType = BaseErrorView.ERROR_GENERAL)
            })
            userPublicObserver.observe(this@HomeActivity, Observer {
                setupUserView(userMapper.mapToIntent(it))
            })
        }
    }

    private fun setupUserView(data: UserIntent) {
        mLayout.ivProfile.setImageUrl(data.profilePhoto ?: "")
        mLayout.tvName.text = data.name
    }

    private fun setupRecyclerProject() {
        projectAdapter = ProjectAdapter(this)
        mLayout.recyclerProject.apply {
            setupRecyclerViewLinear(this@HomeActivity, RecyclerView.VERTICAL)
            adapter = projectAdapter
        }
    }

    private fun setupDateView(date: Date) {
        val dateFormat = SimpleDateFormat(DATE_FORMAT_HOME, Locale.getDefault())
        mLayout.tvDate.text = dateFormat.format(date)
    }

    private fun setupClickListener() {
        mLayout.btnAddProject.setOnClickListener { this.onClickAddProject() }
        mLayout.btnEditProfile.setOnClickListener { this.onClickEditProfile() }
    }
}