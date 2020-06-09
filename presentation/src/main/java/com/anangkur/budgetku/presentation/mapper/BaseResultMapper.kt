package com.anangkur.budgetku.presentation.mapper

import com.anangkur.budgetku.domain.model.BaseResult
import com.anangkur.budgetku.presentation.model.BaseResultView

class BaseResultMapper<T>: Mapper<BaseResultView<T>, BaseResult<T>> {

    companion object {
        private var INSTANCE = null
        fun <T> getInstance() = INSTANCE ?: BaseResultMapper<T>()
    }

    override fun mapToView(type: BaseResult<T>): BaseResultView<T> {
        return BaseResultView(type.status, type.data, type.message, type.isLoading)
    }
}