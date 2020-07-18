package com.anangkur.budgetku.data.mapper.budget

import com.anangkur.budgetku.data.mapper.Mapper
import com.anangkur.budgetku.data.model.budget.ProjectEntity
import com.anangkur.budgetku.domain.model.budget.Project

class ProjectMapper(
    private val categoryProjectMapper: CategoryProjectMapper
) : Mapper<ProjectEntity, Project> {

    companion object {
        private var INSTANCE: ProjectMapper? = null
        fun getInstance() = INSTANCE ?: ProjectMapper(CategoryProjectMapper.getInstance())
    }

    override fun mapToEntity(type: Project): ProjectEntity {
        return ProjectEntity(
            id = type.id,
            title = type.title,
            listCategory = type.listCategory.map { categoryProjectMapper.mapToEntity(it) },
            endDate = type.endDate,
            startDate = type.startDate
        )
    }

    override fun mapFromEntity(type: ProjectEntity): Project {
        return Project(
            id = type.id,
            title = type.title,
            listCategory = type.listCategory.map { categoryProjectMapper.mapFromEntity(it) },
            endDate = type.endDate,
            startDate = type.startDate
        )
    }
}