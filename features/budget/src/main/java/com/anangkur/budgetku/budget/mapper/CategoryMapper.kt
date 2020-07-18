package com.anangkur.budgetku.budget.mapper

import com.anangkur.budgetku.budget.model.CategoryUiModel
import com.anangkur.budgetku.mapper.Mapper
import com.anangkur.budgetku.presentation.model.budget.CategoryView

class CategoryMapper : Mapper<CategoryUiModel, CategoryView> {

    companion object{
        private var INSTANCE: CategoryMapper? = null
        fun getInstance() = INSTANCE ?: CategoryMapper()
    }

    override fun mapToIntent(type: CategoryView): CategoryUiModel {
        return CategoryUiModel(
            title = type.title,
            image = type.image,
            child = type.child.map {
                CategoryUiModel(
                    title = it.title,
                    image = it.image
                )
            }
        )
    }

    override fun mapFromIntent(type: CategoryUiModel): CategoryView {
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
}