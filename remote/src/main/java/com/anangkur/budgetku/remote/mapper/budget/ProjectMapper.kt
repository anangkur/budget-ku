package com.anangkur.budgetku.remote.mapper.budget

import com.anangkur.budgetku.data.model.budget.ProjectEntity
import com.anangkur.budgetku.remote.mapper.Mapper
import com.anangkur.budgetku.remote.model.budget.ProjectRemote

class ProjectMapper(
    private val categoryProjectMapper: CategoryProjectMapper
) : Mapper<ProjectRemote, ProjectEntity> {

    companion object{
        private var INSTANCE: ProjectMapper? = null
        fun getInstance() = INSTANCE
            ?: ProjectMapper(CategoryProjectMapper.getInstance())
    }

    override fun mapFromRemote(type: ProjectRemote): ProjectEntity {
        return ProjectEntity(
            title = type.title,
            startDate = type.startDate,
            endDate = type.endDate,
            listCategory = type.listCategory.map {
                categoryProjectMapper.mapFromRemote(it)
            }
        )
    }

    override fun mapToRemote(type: ProjectEntity): ProjectRemote {
        return ProjectRemote(
            title = type.title,
            startDate = type.startDate,
            endDate = type.endDate,
            listCategory = type.listCategory.map {
                categoryProjectMapper.mapToRemote(it)
            }
        )
    }
}