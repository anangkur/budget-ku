package com.anangkur.budgetku.dashboard.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.anangkur.budgetku.base.BaseAdapter
import com.anangkur.budgetku.dashboard.databinding.ItemProjectBinding
import com.anangkur.budgetku.dashboard.model.ItemProjectIntent

class ProjectAdapter: BaseAdapter<ItemProjectBinding, ItemProjectIntent>() {
    override fun bindView(parent: ViewGroup): ItemProjectBinding {
        return ItemProjectBinding.inflate(LayoutInflater.from(parent.context))
    }

    override fun bind(data: ItemProjectIntent, itemView: ItemProjectBinding, position: Int) {
        itemView.apply {
            tvProjectName.text = data.title
            tvSpendPercentage.text = data.spendPercentage
            tvPeriod.text = data.period
            pbProject.progress = data.progress ?: 0
        }
    }
}