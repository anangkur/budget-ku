package com.anangkur.budgetku.remote.mapper.budget

import com.anangkur.budgetku.data.model.budget.CategoryProjectEntity
import com.anangkur.budgetku.remote.mapper.Mapper
import com.anangkur.budgetku.remote.model.budget.CategoryProjectRemote

class CategoryProjectMapper: Mapper<CategoryProjectRemote, CategoryProjectEntity> {

    companion object{
        private var INSTANCE: CategoryProjectMapper? = null
        fun getInstance() = INSTANCE
            ?: CategoryProjectMapper()
    }

    override fun mapFromRemote(type: CategoryProjectRemote): CategoryProjectEntity {
        return CategoryProjectEntity(
            title = type.title,
            value = type.value,
            image = type.image,
            spend = type.spend
        )
    }

    override fun mapToRemote(type: CategoryProjectEntity): CategoryProjectRemote {
        return CategoryProjectRemote(
            title = type.title,
            value = type.value,
            image = type.image,
            spend = type.spend
        )
    }
}