package com.anangkur.beritaku.remote.mapper

interface Mapper<in MODEL, out T> {
    fun mapFromRemote(type: MODEL): T
}