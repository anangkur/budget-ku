package com.anangkur.budgetku.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.anangkur.budgetku.BuildConfig
import com.anangkur.budgetku.base.BaseActivity
import com.anangkur.budgetku.databinding.ActivitySplashBinding
import com.anangkur.budgetku.presentation.features.app.SplashViewModel
import com.anangkur.budgetku.utils.gone
import com.anangkur.budgetku.utils.obtainViewModel
import com.anangkur.budgetku.utils.showToastShort
import com.anangkur.budgetku.utils.visible
import com.google.android.gms.tasks.Task

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
        var i: Intent
        handler.postDelayed({
            i = if (isLoggedIn){
                intent.setClassName(BuildConfig.APPLICATION_ID, "com.anangkur.budgetku.news.NewsActivity")
            }else{
                intent.setClassName(BuildConfig.APPLICATION_ID, "com.anangkur.budgetku.auth.view.signIn.SignInActivity")
            }
            startActivity(i)
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
