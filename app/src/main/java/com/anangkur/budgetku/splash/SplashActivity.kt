package com.anangkur.budgetku.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.anangkur.budgetku.base.BaseActivity
import com.anangkur.budgetku.databinding.ActivitySplashBinding
import com.anangkur.budgetku.presentation.features.app.SplashViewModel
import com.anangkur.budgetku.utils.Navigation.goToHomeActivity
import com.anangkur.budgetku.utils.Navigation.goToSignInActivity
import com.anangkur.budgetku.utils.gone
import com.anangkur.budgetku.utils.obtainViewModel
import com.anangkur.budgetku.utils.showToastShort
import com.anangkur.budgetku.utils.visible

class SplashActivity: BaseActivity<ActivitySplashBinding, SplashViewModel>(){

    override val mViewModel: SplashViewModel
        get() = obtainViewModel(SplashViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = null
    override val mTitleToolbar: String?
        get() = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeViewModel()
        mViewModel.getTokenFirebase()
        mViewModel.checkLogin()
    }

    override fun setupView(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    private fun openActivity(isLoggedIn: Boolean){
        val handler = Handler()
        handler.postDelayed({
            if (isLoggedIn){
                goToHomeActivity()
            }else{
                goToSignInActivity()
            }
            finish()
        }, 1000)
    }

    private fun observeViewModel(){
        mViewModel.apply {
            progressGetProfile.observe(this@SplashActivity, Observer {
                setupLoading(it)
            })
            successGetProfile.observe(this@SplashActivity, Observer {
                openActivity(it)
            })
            errorGetProfile.observe(this@SplashActivity, Observer {
                showToastShort(it)
                finish()
            })
        }
    }

    private fun setupLoading(isLoading: Boolean){
        if (isLoading){
            mLayout.pbSplash.visible()
        }else{
            mLayout.pbSplash.gone()
        }
    }

}
