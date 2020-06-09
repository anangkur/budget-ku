package com.anangkur.beritaku.presentation.mapper

import com.anangkur.beritaku.domain.model.BaseResult
import com.anangkur.beritaku.presentation.model.BaseResultView

class BaseResultMapper<T>: Mapper<BaseResultView<T>, BaseResult<T>> {

    companion object {
        private var INSTANCE = null
        fun <T> getInstance() = INSTANCE ?: BaseResultMapper<T>()
    }

    override fun mapToView(type: BaseResult<T>): BaseResultView<T> {
        return BaseResultView(type.status, type.data, type.message, type.isLoading)
    }
}