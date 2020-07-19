package com.anangkur.budgetku.presentation.mapper.budget

import com.anangkur.budgetku.domain.model.budget.CategoryProject
import com.anangkur.budgetku.presentation.mapper.Mapper
import com.anangkur.budgetku.presentation.model.budget.CategoryProjectView

class CategoryProjectMapper :
    Mapper<CategoryProjectView, CategoryProject> {

    companion object{
        private var INSTANCE: CategoryProjectMapper? = null
        fun getInstance() = INSTANCE
            ?: CategoryProjectMapper()
    }

    override fun mapToView(type: CategoryProject): CategoryProjectView {
        return CategoryProjectView(
            id = type.id,
            title = type.title,
            image = type.image,
            value = type.value,
            spend = type.spend
        )
    }

    override fun mapFromView(type: CategoryProjectView): CategoryProject {
        return CategoryProject(
            id = type.id,
            title = type.title,
            image = type.image,
            value = type.value,
            spend = type.spend
        )
    }
}