package com.anangkur.budgetku.remote.mapper.budget

import com.anangkur.budgetku.data.model.budget.SpendEntity
import com.anangkur.budgetku.remote.mapper.Mapper
import com.anangkur.budgetku.remote.model.budget.SpendRemote

class SpendMapper : Mapper<SpendRemote, SpendEntity> {

    companion object {
        private var INSTANCE: SpendMapper? = null
        fun getInstance() = INSTANCE ?: SpendMapper()
    }

    override fun mapToRemote(type: SpendEntity): SpendRemote {
        return SpendRemote(
            image = type.image,
            spend = type.spend,
            title = type.title,
            date = type.date,
            idProject = type.idProject,
            idCategory = type.idCategory
        )
    }

    override fun mapFromRemote(type: SpendRemote): SpendEntity {
        return SpendEntity(
            image = type.image,
            spend = type.spend,
            title = type.title,
            date = type.date,
            idProject = type.idProject,
            idCategory = type.idCategory
        )
    }
}