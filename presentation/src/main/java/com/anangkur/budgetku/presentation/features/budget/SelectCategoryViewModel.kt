package com.anangkur.budgetku.presentation.features.budget

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.presentation.model.budget.CategoryView

class SelectCategoryViewModel : ViewModel() {

    private val listCategoryInternalSetter = MutableLiveData<List<CategoryView>>()
    val listCategoryPublicObserver: LiveData<List<CategoryView>> = listCategoryInternalSetter

    fun createDummyListCategory() {
        val listCategory = ArrayList<CategoryView>()
        for (i in 0 until 10) {
            listCategory.add(
                CategoryView(
                    title = "Food",
                    child = listOf(
                        CategoryView(title = "Food", image = "", child = listOf()),
                        CategoryView(title = "Food", image = "", child = listOf()),
                        CategoryView(title = "Food", image = "", child = listOf()),
                        CategoryView(title = "Food", image = "", child = listOf()),
                        CategoryView(title = "Food", image = "", child = listOf())
                    ),
                    image = ""
                )
            )
        }
        listCategoryInternalSetter.postValue(listCategory)
    }
}