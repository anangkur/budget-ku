package com.anangkur.budgetku.remote.mapper

interface Mapper<MODEL, T> {
    fun mapFromRemote(type: MODEL): T
    fun mapToRemote(type: T): MODEL
}