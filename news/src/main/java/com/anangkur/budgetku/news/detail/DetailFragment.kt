package com.anangkur.budgetku.news.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.anangkur.budgetku.news.databinding.FragmentDetailBinding
import com.anangkur.budgetku.base.BaseFragment
import com.anangkur.budgetku.mapper.ArticleMapper
import com.anangkur.budgetku.model.ArticleIntent
import com.anangkur.budgetku.news.NewsActivity
import com.anangkur.budgetku.news.R
import com.anangkur.budgetku.R as appR
import com.anangkur.budgetku.presentation.features.news.NewsViewModel
import com.anangkur.budgetku.utils.setImageUrl

class DetailFragment: BaseFragment<FragmentDetailBinding, NewsViewModel>(), DetailActivityActionListener {

    override val mViewModel: NewsViewModel
        get() = (requireActivity() as NewsActivity).mViewModel
    override val mToolbar: Toolbar?
        get() = (requireActivity() as NewsActivity).mToolbar

    private val mapper = ArticleMapper()

    private var articleIntent: ArticleIntent? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getIntentData()
        setupDataToView(articleIntent)
        mLayout.btnReadMore.setOnClickListener { this.onClickSeeOriginal(articleIntent?.url?:"") }
    }

    private fun getIntentData(){
        articleIntent = mViewModel.selectedNews?.let { mapper.mapToIntent(it) }
    }

    private fun setupDataToView(data: ArticleIntent?){
        mLayout.tvTitleDetail.text = data?.title
        mLayout.tvContentDetail.text = data?.content
        mLayout.ivDetail.setImageUrl(data?.urlToImage?:"")
    }

    override fun onClickSeeOriginal(url: String) {
        mViewModel.originalNewsUrl = url
        findNavController().navigate(R.id.action_detail_fragment_to_original_news_fragment)
    }

    override fun setupToolbar(toolbar: Toolbar?) {
        toolbar?.title = mViewModel.selectedNews?.title
        toolbar?.navigationIcon = ContextCompat.getDrawable(requireContext(), appR.drawable.ic_arrow_back_black_24dp)
        toolbar?.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }

    override fun setupView(container: ViewGroup?): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(LayoutInflater.from(requireContext()), container, false)
    }
}