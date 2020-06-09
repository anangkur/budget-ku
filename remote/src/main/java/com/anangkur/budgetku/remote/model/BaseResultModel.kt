package com.anangkur.budgetku.remote.model

data class BaseResultModel<out T>(
    val status: Int,
    val data: T?,
    val message: String?,
    val isLoading: Boolean?
) {
    companion object {

        private const val STATE_SUCCESS = 1
        private const val STATE_ERROR = -1

        fun <T> success(data: T): BaseResultModel<T> {
            return BaseResultModel(
                STATE_SUCCESS,
                data,
                null,
                null
            )
        }

        fun <T> error(message: String): BaseResultModel<T> {
            return BaseResultModel(
                STATE_ERROR,
                null,
                message,
                null
            )
        }
    }
}