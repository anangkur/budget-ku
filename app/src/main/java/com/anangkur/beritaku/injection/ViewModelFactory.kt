package com.anangkur.beritaku.injection

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anangkur.beritaku.domain.GetArticles
import com.anangkur.beritaku.domain.model.Article
import com.anangkur.beritaku.presentation.features.news.NewsViewModel
import com.anangkur.beritaku.presentation.mapper.ArticleMapper
import com.anangkur.beritaku.presentation.mapper.BaseResultMapper

class ViewModelFactory(
    private val getArticles: GetArticles,
    private val articleMapper: ArticleMapper,
    private val baseResultMapper: BaseResultMapper<List<Article>>
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T  =
        with(modelClass) {
            when {
                isAssignableFrom(NewsViewModel::class.java) -> NewsViewModel(getArticles, articleMapper, baseResultMapper)
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object{
        @Volatile private var INSTANCE: ViewModelFactory? = null
        fun getInstance(context: Context) = INSTANCE ?: synchronized(ViewModelFactory::class.java){
            INSTANCE ?: ViewModelFactory(
                Injection.provideGetArticle(context),
                ArticleMapper.getInstance(),
                BaseResultMapper.getInstance()
            ).also { INSTANCE = it }
        }
    }
}