package com.anangkur.budgetku.budget.mapper

interface Mapper<UI_MODEL, T> {
    fun mapFromUiModel(data: UI_MODEL): T
    fun mapToUiModel(data: T): UI_MODEL
}