package com.anangkur.budgetku.budget.view.selectCategory

import android.view.LayoutInflater
import android.view.ViewGroup
import com.anangkur.budgetku.base.BaseAdapter
import com.anangkur.budgetku.budget.databinding.ItemCategoryBinding
import com.anangkur.budgetku.budget.model.CategoryUiModel
import com.anangkur.budgetku.utils.setImageUrl

class CategoryChildAdapter(private val listener: SelectCategoryActionListener) : BaseAdapter<ItemCategoryBinding, CategoryUiModel>() {
    override fun bindView(parent: ViewGroup): ItemCategoryBinding {
        return ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun bind(data: CategoryUiModel, itemView: ItemCategoryBinding, position: Int) {
        itemView.apply {
            ivCategory.setImageUrl(data.image)
            tvCategory.text = data.title
            root.setOnClickListener { listener.onClickCategory(data) }
        }
    }
}