package com.anangkur.beritaku.data.mapper

import com.anangkur.beritaku.data.model.BaseResultEntity
import com.anangkur.beritaku.domain.model.BaseResult

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