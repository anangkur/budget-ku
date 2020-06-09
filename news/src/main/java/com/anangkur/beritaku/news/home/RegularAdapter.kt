package com.anangkur.beritaku.news.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.anangkur.beritaku.base.BaseAdapter
import com.anangkur.beritaku.model.ArticleIntent
import com.anangkur.beritaku.news.databinding.ItemRegularBinding
import com.anangkur.beritaku.utils.setImageUrl

class RegularAdapter(private val listener: HomeActionListener): BaseAdapter<ItemRegularBinding, ArticleIntent>() {

    override fun bindView(parent: ViewGroup): ItemRegularBinding {
        return ItemRegularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun bind(data: ArticleIntent, itemView: ItemRegularBinding, position: Int) {
        itemView.ivItemRegular.setImageUrl(data.urlToImage?:"")
        itemView.tvItemRegular.text = data.title
        itemView.root.setOnClickListener { listener.onClickItem(data) }
    }
}