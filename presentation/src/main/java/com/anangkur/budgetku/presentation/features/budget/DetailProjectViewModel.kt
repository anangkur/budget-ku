package com.anangkur.budgetku.presentation.features.budget

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.presentation.model.budget.CategoryProjectView
import com.anangkur.budgetku.presentation.model.dashboard.ProjectView

class DetailProjectViewModel : ViewModel() {

    var categorySelectedPosition = 0
    var categorySelectedValue: CategoryProjectView? = null

    var listCategory: List<CategoryProjectView> = emptyList()

    private val listCategoryInternalSetter = MutableLiveData<List<CategoryProjectView>>()
    val listCategoryPublicObserver: LiveData<List<CategoryProjectView>> = listCategoryInternalSetter
    private fun getListCategory(data: List<CategoryProjectView>) {
        listCategory = data
        listCategoryInternalSetter.postValue(listCategory)
    }

    var spendValue: Double = 0.0

    private val budgetInternalSetter = MutableLiveData<ProjectView>()
    val budgetPublicObserver: LiveData<ProjectView> = budgetInternalSetter
    fun setProjectDetail(data: ProjectView) {
        budgetInternalSetter.postValue(data)
        getListCategory(data.listCategory)
    }
}