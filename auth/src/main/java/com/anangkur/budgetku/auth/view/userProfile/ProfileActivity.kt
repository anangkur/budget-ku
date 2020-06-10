package com.anangkur.budgetku.auth.view.userProfile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.anangkur.budgetku.auth.R
import com.anangkur.budgetku.auth.databinding.ActivityProfileBinding
import com.anangkur.budgetku.auth.mapper.UserMapper
import com.anangkur.budgetku.auth.model.UserIntent
import com.anangkur.budgetku.auth.view.editPassword.EditPasswordActivity
import com.anangkur.budgetku.auth.view.editProfile.EditProfileActivity
import com.anangkur.budgetku.auth.view.signIn.SignInActivity
import com.anangkur.budgetku.base.BaseActivity
import com.anangkur.budgetku.presentation.features.auth.ProfileViewModel
import com.anangkur.budgetku.utils.*

class ProfileActivity: BaseActivity<ActivityProfileBinding, ProfileViewModel>(),
    ProfileActionListener {

    companion object{
        // provider
        const val PROVIDER_GOOGLE = "google.com"
        const val PROVIDER_FIREBASE = "firebase"
        const val PROVIDER_PASSWORD = "password"

        fun startActivity(context: Context){
            context.startActivity(Intent(context, ProfileActivity::class.java))
        }
    }

    override val mViewModel: ProfileViewModel
        get() = obtainViewModel(ProfileViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = mLayout.toolbar
    override val mTitleToolbar: String?
        get() = getString(R.string.toolbar_profile)

    private val userMapper = UserMapper.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeViewModel()
        mLayout.btnLogout.setOnClickListener { this.onClickLogout() }
        mLayout.btnEditProfile.setOnClickListener { this.onClickEditProfile() }
        mLayout.btnEditPassword.setOnClickListener { this.onClickEditPassword() }
    }

    override fun setupView(): ActivityProfileBinding {
        return ActivityProfileBinding.inflate(layoutInflater)
    }

    override fun onResume() {
        super.onResume()
        mViewModel.getUserProfile()
    }

    private fun observeViewModel(){
        mViewModel.apply {
            progressGetProfile.observe(this@ProfileActivity, Observer {
                setupProgressGetProfile(it)
            })
            successGetProfile.observe(this@ProfileActivity, Observer {
                setupView(userMapper.mapToIntent(it))
            })
            errorGetProfile.observe(this@ProfileActivity, Observer {
                showSnackbarLong(it)
            })
            progressLogout.observe(this@ProfileActivity, Observer {
                setupProgressLogout(it)
            })
            successLogout.observe(this@ProfileActivity, Observer {
                SignInActivity.startActivityClearStack(this@ProfileActivity)
            })
            errorLogout.observe(this@ProfileActivity, Observer {
                showSnackbarLong(it)
            })
        }
    }

    private fun setupView(data: UserIntent){
        mLayout.layoutProfile.visible()
        mLayout.tvName.text = data.name
        mLayout.tvEmail.text = data.email
        mLayout.ivProfile.setImageUrl(data.photo)
        mLayout.ivProfile.setOnClickListener { this.onClickImage(data.photo) }
        setupEditPassword(data.providerName)
    }

    private fun setupProgressGetProfile(isLoading: Boolean){
        if (isLoading){
            mLayout.pbProfile.visible()
            mLayout.layoutProfile.invisible()
        }else{
            mLayout.pbProfile.gone()
            mLayout.layoutProfile.visible()
        }
    }

    private fun setupProgressLogout(isLoading: Boolean){
        if (isLoading){
            mLayout.tvLogout.gone()
            mLayout.pbLogout.visible()
        }else{
            mLayout.tvLogout.visible()
            mLayout.pbLogout.gone()
        }
    }

    private fun setupEditPassword(providerId: String){
        when (providerId){
            PROVIDER_FIREBASE -> { }
            PROVIDER_GOOGLE -> { mLayout.btnEditPassword.gone() }
            PROVIDER_PASSWORD -> { mLayout.btnEditPassword.visible() }
        }
    }

    override fun onClickEditProfile() {
        EditProfileActivity.startActivity(this)
    }

    override fun onClickEditPassword() {
        EditPasswordActivity.startActivity(this)
    }

    override fun onClickLogout() {
        mViewModel.logout()
    }

    override fun onCLickAbout() {
        showSnackbarLong("Open About Activity")
    }

    override fun onClickImage(imageUrl: String) {
        this.showPreviewImage(imageUrl)
    }
}
