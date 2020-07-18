package com.anangkur.budgetku.mapper

import com.anangkur.budgetku.model.CategoryProjectIntent
import com.anangkur.budgetku.presentation.model.budget.CategoryProjectView

class CategoryProjectMapper : Mapper<CategoryProjectIntent, CategoryProjectView> {

    companion object{
        private var INSTANCE: CategoryProjectMapper? = null
        fun getInstance() = INSTANCE
            ?: CategoryProjectMapper()
    }
    
    override fun mapToIntent(type: CategoryProjectView): CategoryProjectIntent {
        return CategoryProjectIntent(
            id = type.id,
            spend = type.spend,
            title = type.title,
            image = type.image,
            value = type.value,
            remaining = type.value - type.spend
        )
    }

    override fun mapFromIntent(type: CategoryProjectIntent): CategoryProjectView {
        return CategoryProjectView(
            id = type.id,
            title = type.title,
            value = type.value,
            image = type.image,
            spend = type.spend
        )
    }
}