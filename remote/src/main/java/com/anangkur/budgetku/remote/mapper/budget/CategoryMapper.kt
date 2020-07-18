package com.anangkur.budgetku.remote.mapper.budget

import com.anangkur.budgetku.data.model.budget.CategoryEntity
import com.anangkur.budgetku.remote.mapper.Mapper
import com.anangkur.budgetku.remote.model.budget.CategoryRemote

class CategoryMapper : Mapper<CategoryRemote, CategoryEntity> {

    companion object{
        private var INSTANCE: CategoryMapper? = null
        fun getInstance() = INSTANCE ?: CategoryMapper()
    }

    override fun mapFromRemote(type: CategoryRemote): CategoryEntity {
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

    override fun mapToRemote(type: CategoryEntity): CategoryRemote {
        return CategoryRemote(
            title = type.title,
            image = type.image,
            child = type.child.map {
                CategoryRemote(
                    title = it.title,
                    image = it.image
                )
            }
        )
    }
}