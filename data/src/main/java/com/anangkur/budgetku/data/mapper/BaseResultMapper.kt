package com.anangkur.budgetku.data.mapper

import com.anangkur.budgetku.data.model.BaseResultEntity
import com.anangkur.budgetku.domain.model.BaseResult

class BaseResultMapper<T>: Mapper<BaseResultEntity<T>, BaseResult<T>> {

    companion object {
        private var INSTANCE = null
        fun <T> getInstance() = INSTANCE ?: BaseResultMapper<T>()
    }

    override fun mapToEntity(type: BaseResult<T>): BaseResultEntity<T> {
        return BaseResultEntity(type.status, type.data, type.message, type.isLoading)
    }

    override fun mapFromEntity(type: BaseResultEntity<T>): BaseResult<T> {
        return BaseResult(type.status, type.data, type.message, type.isLoading)
    }
}