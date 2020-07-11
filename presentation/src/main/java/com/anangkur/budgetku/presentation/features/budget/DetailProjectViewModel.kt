package com.anangkur.budgetku.presentation.features.budget

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.presentation.model.budget.BudgetView
import com.anangkur.budgetku.presentation.model.budget.CategoryProjectView
import com.anangkur.budgetku.presentation.model.budget.CategoryView
import com.anangkur.budgetku.presentation.model.budget.SpendCategoryView
import java.util.*
import kotlin.collections.ArrayList

class DetailProjectViewModel : ViewModel() {

    var categorySelectedPosition = 0
    var categorySelectedValue: CategoryView? = null

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

    var spendValue: Double = 0.0

    private val listSpendCategoryInternalSetter = MutableLiveData<List<SpendCategoryView>>()
    val listSpendCategoryPublicObserver: LiveData<List<SpendCategoryView>> = listSpendCategoryInternalSetter
    fun createDummyListSpendCategory() {
        val listSpendCategory = ArrayList<SpendCategoryView>()
        for (i in 0 until 20) {
            listSpendCategory.add(
                SpendCategoryView(
                    image = "",
                    title = "Food",
                    remaining = 200000
                )
            )
        }
        listSpendCategoryInternalSetter.postValue(listSpendCategory)
    }

    private val budgetInternalSetter = MutableLiveData<BudgetView>()
    val budgetPublicObserver: LiveData<BudgetView> = budgetInternalSetter
    fun createDummyBudget() {
        budgetInternalSetter.postValue(
            BudgetView(
                totalSpend = 2000000,
                totalRemaining = 1000000,
                totalBudget = 3000000
            )
        )
    }
}