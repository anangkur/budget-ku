package com.anangkur.beritaku.news

import androidx.appcompat.widget.Toolbar
import com.anangkur.beritaku.base.BaseActivity
import com.anangkur.beritaku.news.databinding.ActivityNewsBinding
import com.anangkur.beritaku.utils.obtainViewModel
import com.anangkur.beritaku.presentation.features.news.NewsViewModel
import com.anangkur.beritaku.R as appR

class NewsActivity: BaseActivity<ActivityNewsBinding, NewsViewModel>() {

    override val mLayout: ActivityNewsBinding
        get() = ActivityNewsBinding.inflate(layoutInflater)
    override val mViewModel: NewsViewModel
        get() = obtainViewModel(NewsViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = findViewById(appR.id.toolbar)
}