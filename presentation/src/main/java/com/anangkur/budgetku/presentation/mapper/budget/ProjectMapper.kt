package com.anangkur.budgetku.presentation.mapper.budget

import com.anangkur.budgetku.domain.model.budget.Project
import com.anangkur.budgetku.presentation.mapper.Mapper
import com.anangkur.budgetku.presentation.model.dashboard.ProjectView

class ProjectMapper(
    private val categoryProjectMapper: CategoryProjectMapper
) : Mapper<ProjectView, Project> {

    companion object{
        private var INSTANCE: ProjectMapper? = null
        fun getInstance() = INSTANCE
            ?: ProjectMapper(
                CategoryProjectMapper.getInstance()
            )
    }

    override fun mapToView(type: Project): ProjectView {
        return ProjectView(
            id = type.id,
            title = type.title,
            startDate = type.startDate,
            endDate = type.endDate,
            listCategory = type.listCategory.map {
                categoryProjectMapper.mapToView(it)
            }
        )
    }

    override fun mapFromView(type: ProjectView): Project {
        return Project(
            id = type.id,
            title = type.title,
            startDate = type.startDate,
            endDate = type.endDate,
            listCategory = type.listCategory.map {
                categoryProjectMapper.mapFromView(it)
            }
        )
    }
}