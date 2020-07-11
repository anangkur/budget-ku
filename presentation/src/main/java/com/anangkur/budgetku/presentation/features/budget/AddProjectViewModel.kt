package com.anangkur.budgetku.presentation.features.budget

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.presentation.model.budget.CategoryView

class AddProjectViewModel : ViewModel() {

    var categorySelectedPosition = 0
    var categorySelectedValue: CategoryView? = null
    var budgetValue = 0.0

    val listCategory = ArrayList<CategoryView>()

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
}