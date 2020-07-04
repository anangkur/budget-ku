package com.anangkur.budgetku.budget.mapper

import com.anangkur.budgetku.budget.model.SpendUiModel
import com.anangkur.budgetku.mapper.Mapper
import com.anangkur.budgetku.presentation.model.budget.SpendView

class SpendMapper : Mapper<SpendUiModel, SpendView> {

    companion object{
        private var INSTANCE: SpendMapper? = null
        fun getInstance() = INSTANCE ?: SpendMapper()
    }

    override fun mapToIntent(type: SpendView): SpendUiModel {
        return SpendUiModel(
            image = type.image,
            title = type.title,
            date = type.date,
            spend = type.spend
        )
    }

    override fun mapFromIntent(type: SpendUiModel): SpendView {
        return SpendView(
            image = type.image,
            title = type.title,
            date = type.date,
            spend = type.spend
        )
    }
}