package com.anangkur.budgetku.mapper

import com.anangkur.budgetku.model.ProjectIntent
import com.anangkur.budgetku.presentation.model.budget.CategoryProjectView
import com.anangkur.budgetku.presentation.model.dashboard.ProjectView

class ProjectMapper(
    private val categoryProjectMapper: CategoryProjectMapper
): Mapper<ProjectIntent, ProjectView> {

    companion object{
        private var INSTANCE: ProjectMapper? = null
        fun getInstance() = INSTANCE
            ?: ProjectMapper(
                CategoryProjectMapper.getInstance()
            )
    }

    override fun mapToIntent(type: ProjectView): ProjectIntent {
        val (value, spend) = countValueAndSpend(type.listCategory)
        val remaining = countRemaining(value, spend)
        val progress = countProgress(value, remaining)
        return ProjectIntent(
            title = type.title,
            spendPercentage = "Remaining: $progress%",
            progress = progress,
            period = "${type.startDate} - ${type.endDate}",
            id = type.id,
            totalBudget = value,
            totalRemaining = remaining,
            totalSpend = spend,
            listCategory = type.listCategory.map { categoryProjectMapper.mapToIntent(it) },
            startDate = type.startDate,
            endDate = type.endDate
        )
    }

    override fun mapFromIntent(type: ProjectIntent): ProjectView {
        return ProjectView(
            id = type.id,
            title = type.title,
            endDate = type.endDate,
            listCategory = type.listCategory.map { categoryProjectMapper.mapFromIntent(it) },
            startDate = type.startDate
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

    private fun countRemaining(value: Double, spend: Double): Double = value - spend

    private fun countProgress(value: Double, remaining: Double): Int {
        return ((remaining/value)*100).toInt()
    }
}