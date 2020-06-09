package com.anangkur.budgetku.news

import androidx.appcompat.widget.Toolbar
import com.anangkur.budgetku.news.databinding.ActivityNewsBinding
import com.anangkur.budgetku.base.BaseActivity
import com.anangkur.budgetku.utils.obtainViewModel
import com.anangkur.budgetku.presentation.features.news.NewsViewModel
import com.anangkur.budgetku.R as appR

class NewsActivity: BaseActivity<ActivityNewsBinding, NewsViewModel>() {

    override val mLayout: ActivityNewsBinding
        get() = ActivityNewsBinding.inflate(layoutInflater)
    override val mViewModel: NewsViewModel
        get() = obtainViewModel(NewsViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = findViewById(appR.id.toolbar)
}