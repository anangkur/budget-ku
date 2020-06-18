package com.anangkur.budgetku.auth.view.signIn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.anangkur.budgetku.BuildConfig
import com.anangkur.budgetku.R
import com.anangkur.budgetku.auth.R as authR
import com.anangkur.budgetku.auth.databinding.ActivitySignInBinding
import com.anangkur.budgetku.base.BaseActivity
import com.anangkur.budgetku.auth.view.forgotPassword.ForgotPasswordActivity
import com.anangkur.budgetku.presentation.features.auth.SignInViewModel
import com.anangkur.budgetku.utils.*
import com.anangkur.budgetku.auth.view.signUp.SignUpActivity
import com.anangkur.budgetku.auth.view.userProfile.ProfileActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class SignInActivity: BaseActivity<ActivitySignInBinding, SignInViewModel>(), SignInActionListener {

    companion object{
        const val RC_SIGN_IN = 9001
        fun startActivity(context: Context){
            context.startActivity(Intent(context, SignInActivity::class.java))
        }
        fun startActivityClearStack(context: Context){
            context.startActivity(Intent(context, SignInActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        }
    }

    override val mViewModel: SignInViewModel
        get() = obtainViewModel(SignInViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = null
    override val mTitleToolbar: String?
        get() = null

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupGoogleSignIn()
        setupView(mLayout)
        setupTextWatcher()
        observeViewModel()
    }

    override fun setupView(): ActivitySignInBinding {
        return ActivitySignInBinding.inflate(layoutInflater)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                mViewModel.firebaseSignInWithGoogle(account)
            } catch (e: ApiException) {
                e.printStackTrace()
                showSnackbarLong(e.localizedMessage?:"")
            }
        }
    }

    private fun observeViewModel(){
        mViewModel.apply {
            progressSignInLive.observe(this@SignInActivity, Observer {
                setupLoading(it)
            })
            resultSignInLive.observe(this@SignInActivity, Observer {
                ProfileActivity.startActivity(this@SignInActivity)
            })
            successCreateUser.observe(this@SignInActivity, Observer {
                ProfileActivity.startActivity(this@SignInActivity)
            })
            errorSignInLive.observe(this@SignInActivity, Observer {
                showSnackbarLong(it)
            })
            progressSignInGoogleLive.observe(this@SignInActivity, Observer {
                setupLoadingGoogle(it)
            })
        }
    }

    private fun setupView(mLayout: ActivitySignInBinding){
        mLayout.btnSignin.setOnClickListener { this.onClickSignIn(mLayout.etEmail.text.toString(), mLayout.etPassword.text.toString()) }
        mLayout.btnForgotPassword.setOnClickListener { this.onClickForgot() }
        mLayout.btnRegister.setOnClickListener { this.onClickSignUp() }
        mLayout.btnSigninGoogle.setOnClickListener { this.onClickGoogle() }
    }

    private fun setupLoading(isLoading: Boolean){
        if (isLoading){
            mLayout.btnSignin.showProgress()
        }else{
            mLayout.btnSignin.hideProgress()
        }
    }

    private fun setupLoadingGoogle(isLoading: Boolean){
        if (isLoading){
            mLayout.pbBtnSigninGoogle.visible()
            mLayout.btnSigninGoogle.gone()
        }else{
            mLayout.pbBtnSigninGoogle.gone()
            mLayout.btnSigninGoogle.visible()
        }
    }

    override fun onClickSignIn(email: String?, password: String?) {
        validateInput(email, password)
    }

    override fun onClickForgot() {
        ForgotPasswordActivity.startActivity(this)
    }

    override fun onClickSignUp() {
        SignUpActivity.startActivity(this)
    }

    override fun onClickGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun validateInput(email: String?, password: String?){
        when {
            email.isNullOrEmpty() -> {
                mLayout.tilEmail.isErrorEnabled = true
                mLayout.tilEmail.error = getString(authR.string.error_email_empty)
            }
            !email.validateEmail() -> {
                mLayout.tilEmail.isErrorEnabled = true
                mLayout.tilEmail.error = getString(authR.string.error_email_not_valid)
            }
            password.isNullOrEmpty() -> {
                mLayout.tilPassword.isErrorEnabled = true
                mLayout.tilPassword.error = getString(authR.string.error_password_empty)
            }
            !password.validatePassword() -> {
                mLayout.tilPassword.isErrorEnabled = true
                mLayout.tilPassword.error = getString(authR.string.error_password_not_valid)
            }
            else -> {
                mLayout.tilEmail.isErrorEnabled = false
                mLayout.tilPassword.isErrorEnabled = false
                mViewModel.firebaseSignIn(email, password)
            }
        }
    }

    private fun setupTextWatcher(){
        mLayout.etEmail.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                when {
                    !s.isNullOrEmpty() || s.toString().validateEmail() -> {
                        mLayout.tilEmail.isErrorEnabled = false
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        mLayout.etPassword.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                when {
                    !s.isNullOrEmpty() || s.toString().validatePassword() -> {
                        mLayout.tilPassword.isErrorEnabled = false
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setupGoogleSignIn(){
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }
}
