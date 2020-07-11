package com.anangkur.budgetku.presentation.features.budget

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.presentation.model.budget.CategoryProjectView
import com.anangkur.budgetku.presentation.model.budget.CategoryView
import java.util.*
import kotlin.collections.ArrayList

class AddProjectViewModel : ViewModel() {

    var categorySelectedPosition = 0
    var categorySelectedValue: CategoryView? = null
    var budgetValue = 0.0
    var startDate: Calendar? = null
    var endDate: Calendar? = null

    val listCategory = ArrayList<CategoryView>()
    private val listCategoryProject = ArrayList<CategoryProjectView>()

    private val listCategoryInternalSetter = MutableLiveData<List<CategoryView>>()
    val listCategoryPublicObserver: LiveData<List<CategoryView>> = listCategoryInternalSetter
    fun createDummyListCategory() {
        listCategory.clear()
        for ( i in 0 until 10 ) {
            listCategory.add(
                CategoryView(
                    title = "Category $i",
                    image = "",
                    child = listOf()
                )
            )
        }
        listCategoryInternalSetter.postValue(listCategory)
    }

    private val listCategoryProjectInternalSetter = MutableLiveData<List<CategoryProjectView>>()
    val listCategoryProjectPublicObserver: LiveData<List<CategoryProjectView>> = listCategoryProjectInternalSetter
    fun addCategoryProject(data: CategoryProjectView) {
        listCategoryProject.add(data)
        listCategoryProjectInternalSetter.postValue(listCategoryProject)
    }

    fun getCategoryProject(): List<CategoryProjectView> = listCategoryProject
}