package com.anangkur.budgetku.dashboard.mapper

import com.anangkur.budgetku.dashboard.model.ProjectIntent
import com.anangkur.budgetku.mapper.Mapper
import com.anangkur.budgetku.presentation.model.budget.CategoryProjectView
import com.anangkur.budgetku.presentation.model.dashboard.ProjectView

class ProjectMapper: Mapper<ProjectIntent, ProjectView> {

    companion object{
        private var INSTANCE: ProjectMapper? = null
        fun getInstance() = INSTANCE ?: ProjectMapper()
    }

    override fun mapToIntent(type: ProjectView): ProjectIntent {
        val (value, spend) = countValueAndSpend(type.listCategory)
        val progress = countProgress(value, spend)
        return ProjectIntent(
            title = type.title,
            spendPercentage = "Remaining: $progress%",
            progress = progress,
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

    private fun countValueAndSpend(data: List<CategoryProjectView>): Pair<Double, Double> {
        var value = 0.0
        var spend = 0.0
        data.forEach {
            value += it.value
            spend += it.spend
        }
        return Pair(value, spend)
    }

    private fun countProgress(value: Double, spend: Double): Int {
        return (((value - spend)/value)*100).toInt()
    }
}