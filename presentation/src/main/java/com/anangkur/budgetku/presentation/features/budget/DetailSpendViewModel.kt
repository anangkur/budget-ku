package com.anangkur.budgetku.presentation.features.budget

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.presentation.model.budget.SpendView

class DetailSpendViewModel : ViewModel() {

    private val listSpendInternalSetter = MutableLiveData<List<SpendView>>()
    val listSpendPublicObserver: LiveData<List<SpendView>> = listSpendInternalSetter

    fun createDummyListSpend() {
        val listSpend = ArrayList<SpendView>()
        for (i in 0 until 100) {
            listSpend.add(
                SpendView(
                    image = "",
                    spend = 1000000,
                    date = "Today, 10:00",
                    title = "Food"
            ))
        }
        listSpendInternalSetter.postValue(listSpend)
    }
}