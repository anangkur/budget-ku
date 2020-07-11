package com.anangkur.budgetku.budget.mapper

import com.anangkur.budgetku.budget.model.CategoryProjectUiModel
import com.anangkur.budgetku.mapper.Mapper
import com.anangkur.budgetku.presentation.model.budget.CategoryProjectView

class CategoryProjectMapper: Mapper<CategoryProjectUiModel, CategoryProjectView> {

    companion object{
        private var INSTANCE: CategoryProjectMapper? = null
        fun getInstance() = INSTANCE ?: CategoryProjectMapper()
    }

    override fun mapToIntent(type: CategoryProjectView): CategoryProjectUiModel {
        return CategoryProjectUiModel(
            title = type.title,
            image = type.image,
            value = type.value
        )
    }

    override fun mapFromIntent(type: CategoryProjectUiModel): CategoryProjectView {
        return CategoryProjectView(
            title = type.title,
            image = type.image,
            value = type.value
        )
    }
}