package com.anangkur.budgetku.data.mapper.budget

import com.anangkur.budgetku.data.mapper.Mapper
import com.anangkur.budgetku.data.model.budget.SpendEntity
import com.anangkur.budgetku.domain.model.budget.Spend

class SpendMapper : Mapper<SpendEntity, Spend> {

    companion object {
        private var INSTANCE: SpendMapper? = null
        fun getInstance() = INSTANCE ?: SpendMapper()
    }

    override fun mapToEntity(type: Spend): SpendEntity {
        return SpendEntity(
            image = type.image,
            spend = type.spend,
            title = type.title,
            date = type.date,
            idProject = type.idProject,
            idCategory = type.idCategory,
            note = type.note
        )
    }

    override fun mapFromEntity(type: SpendEntity): Spend {
        return Spend(
            image = type.image,
            spend = type.spend,
            title = type.title,
            date = type.date,
            idProject = type.idProject,
            idCategory = type.idCategory,
            note = type.note
        )
    }
}