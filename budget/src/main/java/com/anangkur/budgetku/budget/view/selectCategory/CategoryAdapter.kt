package com.anangkur.budgetku.budget.view.selectCategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.budgetku.base.BaseAdapter
import com.anangkur.budgetku.budget.databinding.ItemCategoryBinding
import com.anangkur.budgetku.budget.model.CategoryUiModel
import com.anangkur.budgetku.utils.setImageUrl
import com.anangkur.budgetku.utils.setupRecyclerViewLinear

class CategoryAdapter(private val listener: SelectCategoryActionListener) : BaseAdapter<ItemCategoryBinding, CategoryUiModel>() {

    private lateinit var childAdapter: CategoryChildAdapter

    override fun bindView(parent: ViewGroup): ItemCategoryBinding {
        return ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun bind(data: CategoryUiModel, itemView: ItemCategoryBinding, position: Int) {
        itemView.apply {
            ivCategory.setImageUrl(data.image)
            tvCategory.text = data.title
            root.setOnClickListener {
                if (expandableCategory.isExpanded) expandableCategory.collapse()
                else expandableCategory.expand()
            }

            childAdapter = CategoryChildAdapter(listener)
            recyclerCategoryChild.apply {
                setupRecyclerViewLinear(context, RecyclerView.VERTICAL)
                adapter = childAdapter
            }
            childAdapter.setRecyclerData(data.child)
        }
    }
}