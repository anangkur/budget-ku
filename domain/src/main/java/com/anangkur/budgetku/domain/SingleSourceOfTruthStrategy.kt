package com.anangkur.budgetku.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.anangkur.budgetku.domain.model.BaseResult
import kotlinx.coroutines.Dispatchers

fun <T, A> resultLiveData(
    databaseQuery: () -> T,
    networkCall: suspend () -> BaseResult<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<BaseResult<T>> =
    liveData(Dispatchers.IO) {
        emit(BaseResult.loading(true))
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == BaseResult.STATE_SUCCESS) {
            emit(BaseResult.loading(false))
            saveCallResult(responseStatus.data!!)
            val source = BaseResult.success(databaseQuery.invoke())
            emit(source)
        } else if (responseStatus.status == BaseResult.STATE_ERROR) {
            emit(BaseResult.loading(false))
            emit(BaseResult.error(responseStatus.message!!))
            val source = BaseResult.success(databaseQuery.invoke())
            emit(source)
        }
    }