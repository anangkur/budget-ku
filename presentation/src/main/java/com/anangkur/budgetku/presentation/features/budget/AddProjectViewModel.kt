package com.anangkur.budgetku.presentation.features.budget

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.repository.BudgetRepository
import com.anangkur.budgetku.presentation.mapper.budget.CategoryProjectMapper
import com.anangkur.budgetku.presentation.model.budget.CategoryProjectView
import com.anangkur.budgetku.presentation.model.budget.CategoryView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class AddProjectViewModel(private val budgetRepository: BudgetRepository) : ViewModel() {

    private val categoryProjectMapper = CategoryProjectMapper.getInstance()

    var budgetValue = 0.0
    var startDate: Calendar? = null
    var endDate: Calendar? = null

    var categorySelectedValue: CategoryView? = null

    private val listCategoryProject = ArrayList<CategoryProjectView>()
    private val listCategoryProjectInternalSetter = MutableLiveData<List<CategoryProjectView>>()
    val listCategoryProjectPublicObserver: LiveData<List<CategoryProjectView>> = listCategoryProjectInternalSetter
    fun addCategoryProject(data: CategoryProjectView) {
        listCategoryProject.add(data)
        listCategoryProjectInternalSetter.postValue(listCategoryProject)
    }

    fun deleteCategoryProject(position: Int) {
        listCategoryProject.removeAt(position)
        listCategoryProjectInternalSetter.postValue(listCategoryProject)
    }

    fun getCategoryProject(): List<CategoryProjectView> = listCategoryProject

    val loadingCreateProject = MutableLiveData<Boolean>()
    val successCreateProject = MutableLiveData<Boolean>()
    val errorCreateProject = MutableLiveData<String>()
    fun createProject(
        title: String,
        startDate: String,
        endDate: String,
        category: List<CategoryProjectView>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            budgetRepository.createProject(
                title = title,
                startDate = startDate,
                endDate = endDate,
                category = category.map { categoryProjectMapper.mapFromView(it) },
                listener = object : BaseFirebaseListener<Boolean> {
                    override fun onLoading(isLoading: Boolean) {
                        loadingCreateProject.postValue(isLoading)
                    }
                    override fun onSuccess(data: Boolean) {
                        successCreateProject.postValue(data)
                    }
                    override fun onFailed(errorMessage: String) {
                        errorCreateProject.postValue(errorMessage)
                    }
                }
            )
        }
    }
}