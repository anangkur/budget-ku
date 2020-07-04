package com.anangkur.budgetku.dashboard.mapper

import com.anangkur.budgetku.dashboard.model.ItemProjectIntent
import com.anangkur.budgetku.mapper.Mapper
import com.anangkur.budgetku.presentation.model.dashboard.ItemProjectView

class ItemProjectMapper: Mapper<ItemProjectIntent, ItemProjectView> {

    companion object{
        private var INSTANCE: ItemProjectMapper? = null
        fun getInstance() = INSTANCE ?: ItemProjectMapper()
    }

    override fun mapToIntent(type: ItemProjectView): ItemProjectIntent {
        return ItemProjectIntent(
            title = type.title,
            spendPercentage = type.spendPercentage,
            progress = type.progress,
            period = type.period
        )
    }

    override fun mapFromIntent(type: ItemProjectIntent): ItemProjectView {
        return ItemProjectView(
            title = type.title,
            spendPercentage = type.spendPercentage,
            progress = type.progress,
            period = type.period
        )
    }
}