package com.anangkur.budgetku.data.mapper.budget

import com.anangkur.budgetku.data.mapper.BaseResultMapper
import com.anangkur.budgetku.data.mapper.Mapper
import com.anangkur.budgetku.data.model.budget.CategoryProjectEntity
import com.anangkur.budgetku.domain.model.budget.CategoryProject

class CategoryProjectMapper:
    Mapper<CategoryProjectEntity, CategoryProject> {

    companion object {
        private var INSTANCE: CategoryProjectMapper? = null
        fun getInstance() = INSTANCE ?: CategoryProjectMapper()
    }

    override fun mapToEntity(type: CategoryProject): CategoryProjectEntity {
        return CategoryProjectEntity(
            title = type.title,
            image = type.image,
            value = type.value
        )
    }

    override fun mapFromEntity(type: CategoryProjectEntity): CategoryProject {
        return CategoryProject(
            title = type.title,
            image = type.image,
            value = type.value
        )
    }
}