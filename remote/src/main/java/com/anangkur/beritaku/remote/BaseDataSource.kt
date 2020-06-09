package com.anangkur.beritaku.remote

import com.anangkur.beritaku.remote.model.BaseResultModel
import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): BaseResultModel<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return BaseResultModel.success(body)
            }
            return BaseResultModel.error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return BaseResultModel.error(e.message ?: e.toString())
        }
    }
}