package com.anangkur.budgetku.budget.view.addProject

import android.view.LayoutInflater
import android.view.ViewGroup
import com.anangkur.budgetku.base.BaseAdapter
import com.anangkur.budgetku.budget.databinding.ItemCategoryProjectBinding
import com.anangkur.budgetku.model.CategoryProjectIntent
import com.anangkur.budgetku.utils.currencyFormatToRupiah
import com.anangkur.budgetku.utils.setImageUrl

class CategoryProjectAdapter(
    private val listener: AddProjectActionListener
) : BaseAdapter<ItemCategoryProjectBinding, CategoryProjectIntent>() {

    override fun bindView(parent: ViewGroup): ItemCategoryProjectBinding {
        return ItemCategoryProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun bind(
        data: CategoryProjectIntent,
        itemView: ItemCategoryProjectBinding,
        position: Int
    ) {
        itemView.apply {
            tvSpendCategory.text = data.title
            ivSpend.setImageUrl(data.image)
            tvBudget.text = data.value.currencyFormatToRupiah()
            btnDelete.setOnClickListener { listener.onClickDeleteCategory(position) }
        }
    }

}