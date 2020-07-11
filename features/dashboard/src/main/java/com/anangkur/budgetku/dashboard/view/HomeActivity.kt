package com.anangkur.budgetku.dashboard.view

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.budgetku.base.BaseActivity
import com.anangkur.budgetku.dashboard.R
import com.anangkur.budgetku.dashboard.databinding.ActivityHomeBinding
import com.anangkur.budgetku.dashboard.mapper.ItemProjectMapper
import com.anangkur.budgetku.dashboard.mapper.UserMapper
import com.anangkur.budgetku.dashboard.model.ItemProjectIntent
import com.anangkur.budgetku.dashboard.model.UserIntent
import com.anangkur.budgetku.presentation.features.dashboard.HomeViewModel
import com.anangkur.budgetku.utils.Navigation.goToAddProjectActivity
import com.anangkur.budgetku.utils.Navigation.goToDetailProjectActivity
import com.anangkur.budgetku.utils.Navigation.goToProfileActivity
import com.anangkur.budgetku.utils.obtainViewModel
import com.anangkur.budgetku.utils.setImageUrl
import com.anangkur.budgetku.utils.setupRecyclerViewLinear
import java.text.SimpleDateFormat
import java.util.*
import com.anangkur.budgetku.R as appR

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeActivityActionListener {

    override val mViewModel: HomeViewModel?
        get() = obtainViewModel(HomeViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = findViewById(R.id.toolbar)
    override val mTitleToolbar: String?
        get() = getString(appR.string.app_name)

    private lateinit var projectAdapter: ProjectAdapter

    private val itemProjectMapper = ItemProjectMapper.getInstance()
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
        mViewModel?.createDummyListProject()
        mViewModel?.createDummyUser()
    }

    override fun onClickAddProject() {
        goToAddProjectActivity()
    }

    override fun onClickEditProfile() {
        goToProfileActivity()
    }

    override fun onClickItem(data: ItemProjectIntent) {
        goToDetailProjectActivity()
    }

    private fun observeViewModel() {
        mViewModel?.apply {
            listProjectPublicObserver.observe(this@HomeActivity, Observer {
                projectAdapter.setRecyclerData(it.map { list -> itemProjectMapper.mapToIntent(list) })
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
        val dateFormat = SimpleDateFormat("EEEE, MMM dd yyyy", Locale.getDefault())
        mLayout.tvDate.text = dateFormat.format(date)
    }

    private fun setupClickListener() {
        mLayout.btnAddProject.setOnClickListener { this.onClickAddProject() }
        mLayout.btnEditProfile.setOnClickListener { this.onClickEditProfile() }
    }
}