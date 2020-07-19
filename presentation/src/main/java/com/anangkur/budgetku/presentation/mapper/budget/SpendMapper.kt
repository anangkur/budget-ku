package com.anangkur.budgetku.presentation.mapper.budget

import com.anangkur.budgetku.domain.model.budget.Spend
import com.anangkur.budgetku.presentation.mapper.Mapper
import com.anangkur.budgetku.presentation.model.budget.SpendView

class SpendMapper : Mapper<SpendView, Spend> {

    companion object {
        private var INSTANCE: SpendMapper? = null
        fun getInstance() = INSTANCE ?: SpendMapper()
    }

    override fun mapToView(type: Spend): SpendView {
        return SpendView(
            image = type.image,
            spend = type.spend,
            title = type.title,
            date = type.date,
            idProject = type.idProject
        )
    }

    override fun mapFromView(type: SpendView): Spend {
        return Spend(
            image = type.image,
            spend = type.spend,
            title = type.title,
            date = type.date,
            idProject = type.idProject
        )
    }
}