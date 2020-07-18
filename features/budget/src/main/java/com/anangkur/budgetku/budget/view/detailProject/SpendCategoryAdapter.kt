package com.anangkur.budgetku.budget.view.detailProject

import android.view.LayoutInflater
import android.view.ViewGroup
import com.anangkur.budgetku.base.BaseAdapter
import com.anangkur.budgetku.budget.databinding.ItemSpendCategoryBinding
import com.anangkur.budgetku.model.CategoryProjectIntent
import com.anangkur.budgetku.utils.currencyFormatToRupiah
import com.anangkur.budgetku.utils.setImageUrl

class SpendCategoryAdapter(
    private val listener: DetailProjectActionListener
) : BaseAdapter<ItemSpendCategoryBinding, CategoryProjectIntent>() {
    override fun bindView(parent: ViewGroup): ItemSpendCategoryBinding {
        return ItemSpendCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun bind(data: CategoryProjectIntent, itemView: ItemSpendCategoryBinding, position: Int) {
        itemView.apply {
            ivBudget.setImageUrl(data.image)
            tvBudget.text = data.title
            tvRemaining.text = data.remaining.currencyFormatToRupiah()
            root.setOnClickListener { listener.onClickSpendCategory(data) }
        }
    }
}