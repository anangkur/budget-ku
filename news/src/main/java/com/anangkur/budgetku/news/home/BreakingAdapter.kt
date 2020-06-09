package com.anangkur.budgetku.news.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.anangkur.budgetku.news.databinding.ItemBreakingBinding
import com.anangkur.budgetku.model.ArticleIntent
import com.anangkur.budgetku.base.BaseAdapter
import com.anangkur.budgetku.utils.setImageUrl

class BreakingAdapter(private val listener: HomeActionListener): BaseAdapter<ItemBreakingBinding, ArticleIntent>(){

    override fun bindView(parent: ViewGroup): ItemBreakingBinding {
        return ItemBreakingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun bind(data: ArticleIntent, itemView: ItemBreakingBinding, position: Int) {
        itemView.ivItemBreaking.setImageUrl(data.urlToImage?:"")
        itemView.tvItemBreaking.text = data.title
        itemView.root.setOnClickListener { listener.onClickItem(data) }
    }

}