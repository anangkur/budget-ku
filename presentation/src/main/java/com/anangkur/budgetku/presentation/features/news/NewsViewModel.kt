package com.anangkur.budgetku.presentation.features.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.domain.impl.ArticlesSource
import com.anangkur.budgetku.domain.model.Article
import com.anangkur.budgetku.presentation.mapper.ArticleMapper
import com.anangkur.budgetku.presentation.mapper.BaseResultMapper
import com.anangkur.budgetku.presentation.model.ArticleView

class NewsViewModel (
    private val articlesSource: ArticlesSource,
    private val mapper: ArticleMapper,
    private val baseResultMapper: BaseResultMapper<List<Article>>
): ViewModel(){

    var selectedNews: ArticleView? = null
    var originalNewsUrl = ""

    private val triggerTopHeadlineNews = MutableLiveData<Boolean>()
    val topHeadlineNewsLive = Transformations.switchMap(triggerTopHeadlineNews){
        Transformations.map(articlesSource.getTopHeadlinesNews()){
            baseResultMapper.mapToView(it)
        }
    }
    fun getTopHeadlineNews(){
        triggerTopHeadlineNews.postValue(true)
    }

    private val triggerBusinessNews = MutableLiveData<Boolean>()
    val businessNewsLive = Transformations.switchMap(triggerTopHeadlineNews){
        Transformations.map(articlesSource.getBusinessNews()){
            baseResultMapper.mapToView(it)
        }
    }
    fun getBusinessNews() {
        triggerBusinessNews.postValue(true)
    }

    private val triggerTechNews = MutableLiveData<Boolean>()
    val techNewsLive = Transformations.switchMap(triggerTechNews){
        Transformations.map(articlesSource.getTechNews()){
            baseResultMapper.mapToView(it)
        }
    }
    fun getTechNews() {
        triggerTechNews.postValue(true)
    }

    private val triggerSportNews = MutableLiveData<Boolean>()
    val sportNewsLive = Transformations.switchMap(triggerSportNews){
        Transformations.map(articlesSource.getSportNews()){
            baseResultMapper.mapToView(it)
        }
    }
    fun getSportNews() {
        triggerSportNews.postValue(true)
    }

    fun mapToView(data: List<Article>): List<ArticleView> {
        return data.map { mapper.mapToView(it) }
    }

    val firstTopHeadlineLive = MutableLiveData<ArticleView>()
    val topHeadlineLive = MutableLiveData<List<ArticleView>>()
    fun separateMoviesBreaking(listNews: List<ArticleView>?){
        if (!listNews.isNullOrEmpty()){
            val tempListData = ArrayList<ArticleView>()
            for (i in listNews.indices){
                if (i == 0){
                    firstTopHeadlineLive.postValue(listNews[i])
                }else if (i <= 5){
                    tempListData.add(listNews[i])
                }
            }
            topHeadlineLive.postValue(tempListData)
        }
    }
}