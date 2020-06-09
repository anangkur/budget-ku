package com.anangkur.budgetku.remote.mapper

import com.anangkur.budgetku.data.model.BaseResultEntity
import com.anangkur.budgetku.remote.model.BaseResultModel

class BaseResultMapper<T>: Mapper<BaseResultModel<T>, BaseResultEntity<T>> {

    companion object {
        private var INSTANCE = null
        fun <T> getInstance() = INSTANCE ?: BaseResultMapper<T>()
    }

    override fun mapFromRemote(type: BaseResultModel<T>): BaseResultEntity<T> {
        return BaseResultEntity(type.status, type.data, type.message, type.isLoading)
    }
}