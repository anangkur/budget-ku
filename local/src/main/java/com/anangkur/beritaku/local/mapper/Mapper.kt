package com.anangkur.beritaku.local.mapper

interface Mapper<CACHED, T> {
    fun mapFromCached(type: CACHED): T
    fun mapToCached(type: T): CACHED
}