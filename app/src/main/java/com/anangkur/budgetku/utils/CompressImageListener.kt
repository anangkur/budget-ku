package com.anangkur.budgetku.utils

import java.io.File

interface CompressImageListener {
    fun progress(isLoading: Boolean)
    fun success(data: File)
    fun error(errorMessage: String)
}