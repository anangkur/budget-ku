package com.anangkur.budgetku.data.source.budget

import com.anangkur.budgetku.data.mapper.budget.CategoryMapper
import com.anangkur.budgetku.data.mapper.budget.CategoryProjectMapper
import com.anangkur.budgetku.data.mapper.budget.ProjectMapper
import com.anangkur.budgetku.data.mapper.budget.SpendMapper
import com.anangkur.budgetku.data.model.budget.CategoryEntity
import com.anangkur.budgetku.data.model.budget.ProjectEntity
import com.anangkur.budgetku.data.repository.budget.BudgetDataStore
import com.anangkur.budgetku.data.repository.budget.BudgetRemote
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.model.budget.Category
import com.anangkur.budgetku.domain.model.budget.CategoryProject
import com.anangkur.budgetku.domain.model.budget.Project
import com.anangkur.budgetku.domain.model.budget.Spend

class BudgetRemoteDataStore(
    private val budgetRemote: BudgetRemote
): BudgetDataStore {

    private val categoryProjectMapper = CategoryProjectMapper.getInstance()
    private val categoryMapper = CategoryMapper.getInstance()
    private val projectMapper = ProjectMapper.getInstance()
    private val spendMapper = SpendMapper.getInstance()

    companion object{
        private var INSTANCE: BudgetRemoteDataStore? = null
        fun getInstance(budgetRemote: BudgetRemote) = INSTANCE
            ?: BudgetRemoteDataStore(
                budgetRemote
            )
    }

    override fun createProject(
        title: String,
        startDate: String,
        endDate: String,
        category: List<CategoryProject>,
        listener: BaseFirebaseListener<Boolean>
    ) {
        budgetRemote.createProject(title, startDate, endDate, category.map {
            categoryProjectMapper.mapToEntity(it)
        }, object: com.anangkur.budgetku.data.BaseFirebaseListener<Boolean>{
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: Boolean) {
                listener.onSuccess(data)
            }
            override fun onFailed(errorMessage: String) {
                listener.onFailed(errorMessage)
            }
        })
    }

    override fun getCategory(listener: BaseFirebaseListener<List<Category>>) {
        budgetRemote.getCategory(object: com.anangkur.budgetku.data.BaseFirebaseListener<List<CategoryEntity>>{
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: List<CategoryEntity>) {
                listener.onSuccess(data.map { categoryMapper.mapFromEntity(it) })
            }
            override fun onFailed(errorMessage: String) {
                listener.onFailed(errorMessage)
            }
        })
    }

    override fun getProject(listener: BaseFirebaseListener<List<Project>>) {
        budgetRemote.getProject(object : com.anangkur.budgetku.data.BaseFirebaseListener<List<ProjectEntity>> {
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: List<ProjectEntity>) {
                listener.onSuccess(data.map { projectMapper.mapFromEntity(it) })
            }
            override fun onFailed(errorMessage: String) {
                listener.onFailed(errorMessage)
            }
        })
    }

    override fun createSpend(spend: Spend, listener: BaseFirebaseListener<Boolean>) {
        budgetRemote.createSpend(spendMapper.mapToEntity(spend), object : com.anangkur.budgetku.data.BaseFirebaseListener<Boolean>{
            override fun onLoading(isLoading: Boolean) {
                listener.onLoading(isLoading)
            }
            override fun onSuccess(data: Boolean) {
                listener.onSuccess(data)
            }
            override fun onFailed(errorMessage: String) {
                listener.onFailed(errorMessage)
            }
        })
    }
}