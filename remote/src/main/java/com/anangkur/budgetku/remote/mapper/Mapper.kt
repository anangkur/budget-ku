package com.anangkur.budgetku.remote.mapper

interface Mapper<in MODEL, out T> {
    fun mapFromRemote(type: MODEL): T
}