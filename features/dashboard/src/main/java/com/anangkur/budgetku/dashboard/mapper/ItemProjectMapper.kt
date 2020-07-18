package com.anangkur.budgetku.dashboard.mapper

import com.anangkur.budgetku.dashboard.model.ProjectIntent
import com.anangkur.budgetku.mapper.Mapper
import com.anangkur.budgetku.presentation.model.dashboard.ProjectView

class ItemProjectMapper: Mapper<ProjectIntent, ProjectView> {

    companion object{
        private var INSTANCE: ItemProjectMapper? = null
        fun getInstance() = INSTANCE ?: ItemProjectMapper()
    }

    override fun mapToIntent(type: ProjectView): ProjectIntent {
        return ProjectIntent(
            title = type.title,
            spendPercentage = "",
            progress = 0,
            period = "${type.startDate} - ${type.endDate}"
        )
    }

    override fun mapFromIntent(type: ProjectIntent): ProjectView {
        return ProjectView(
            title = type.title ?: "",
            endDate = "",
            listCategory = emptyList(),
            startDate = ""
        )
    }
}