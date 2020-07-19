package com.anangkur.budgetku.presentation.mapper.budget

import com.anangkur.budgetku.domain.model.budget.Category
import com.anangkur.budgetku.presentation.mapper.Mapper
import com.anangkur.budgetku.presentation.model.budget.CategoryView

class CategoryMapper :
    Mapper<CategoryView, Category> {

    companion object{
        private var INSTANCE: CategoryMapper? = null
        fun getInstance() = INSTANCE
            ?: CategoryMapper()
    }

    override fun mapToView(type: Category): CategoryView {
        return CategoryView(
            title = type.title,
            image = type.image,
            child = type.child.map {
                CategoryView(
                    title = it.title,
                    image = it.image
                )
            }
        )
    }

    override fun mapFromView(type: CategoryView): Category {
        return Category(
            title = type.title,
            image = type.image,
            child = type.child.map {
                Category(
                    title = it.title,
                    image = it.image
                )
            }
        )
    }
}