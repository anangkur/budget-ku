package com.anangkur.budgetku.presentation.features.budget

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.presentation.model.budget.BudgetView
import com.anangkur.budgetku.presentation.model.budget.SpendCategoryView

class DetailProjectViewModel : ViewModel() {

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