package com.anangkur.beritaku.presentation.mapper

interface Mapper<VIEW, T> {
    fun mapToView(type: T): VIEW
}