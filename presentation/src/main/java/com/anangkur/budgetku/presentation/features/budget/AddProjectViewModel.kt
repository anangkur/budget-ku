package com.anangkur.budgetku.presentation.features.budget

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.presentation.model.budget.CategoryProjectView
import com.anangkur.budgetku.presentation.model.budget.CategoryView
import java.util.*
import kotlin.collections.ArrayList

class AddProjectViewModel : ViewModel() {

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

    fun getCategoryProject(): List<CategoryProjectView> = listCategoryProject
}