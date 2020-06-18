package com.anangkur.budgetku.local.mapper

interface Mapper<CACHED, T> {
    fun mapFromCached(type: CACHED): T
    fun mapToCached(type: T): CACHED
}