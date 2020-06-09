package com.anangkur.beritaku.presentation.model

data class BaseResultView<out T>(val status: Int, val data: T?, val message: String?, val isLoading: Boolean?) {

    companion object {
        const val STATE_SUCCESS = 1
        const val STATE_ERROR = -1
        const val STATE_LOADING = 0
    }
}