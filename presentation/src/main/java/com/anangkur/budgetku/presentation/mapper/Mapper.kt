package com.anangkur.budgetku.presentation.mapper

interface Mapper<VIEW, T> {
    fun mapToView(type: T): VIEW
    fun mapFromView(type: VIEW): T
}