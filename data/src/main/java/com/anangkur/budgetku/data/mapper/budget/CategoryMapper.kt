package com.anangkur.budgetku.data.mapper.budget

import com.anangkur.budgetku.data.mapper.Mapper
import com.anangkur.budgetku.data.model.budget.CategoryEntity
import com.anangkur.budgetku.domain.model.budget.Category

class CategoryMapper : Mapper<CategoryEntity, Category> {

    companion object {
        private var INSTANCE: CategoryMapper? = null
        fun getInstance() = INSTANCE ?: CategoryMapper()
    }

    override fun mapToEntity(type: Category): CategoryEntity {
        return CategoryEntity(
            title = type.title,
            image = type.image,
            child = type.child.map {
                CategoryEntity(
                    title = it.title,
                    image = it.image
                )
            }
        )
    }

    override fun mapFromEntity(type: CategoryEntity): Category {
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