package com.anangkur.budgetku.presentation.features.budget

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.repository.BudgetRepository
import com.anangkur.budgetku.presentation.mapper.budget.SpendMapper
import com.anangkur.budgetku.presentation.model.budget.CategoryProjectView
import com.anangkur.budgetku.presentation.model.budget.SpendView
import com.anangkur.budgetku.presentation.model.dashboard.ProjectView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailProjectViewModel(private val budgetRepository: BudgetRepository) : ViewModel() {

    private val spendMapper = SpendMapper.getInstance()

    var categorySelectedPosition = 0
    var categorySelectedValue: CategoryProjectView? = null
    var spendValue: Double = 0.0

    var listCategory: List<CategoryProjectView> = emptyList()
    private val listCategoryInternalSetter = MutableLiveData<List<CategoryProjectView>>()
    val listCategoryPublicObserver: LiveData<List<CategoryProjectView>> = listCategoryInternalSetter
    private fun getListCategory(data: List<CategoryProjectView>) {
        listCategory = data
        listCategoryInternalSetter.postValue(listCategory)
    }

    private val budgetInternalSetter = MutableLiveData<ProjectView>()
    val budgetPublicObserver: LiveData<ProjectView> = budgetInternalSetter
    fun setProjectDetail(data: ProjectView) {
        budgetInternalSetter.postValue(data)
        getListCategory(data.listCategory)
    }

    val loadingCreateSpend = MutableLiveData<Boolean>()
    val successCreateSpend = MutableLiveData<Boolean>()
    val errorCreateSpend = MutableLiveData<String>()
    fun createSpend(spendView: SpendView) {
        CoroutineScope(Dispatchers.IO).launch {
            budgetRepository.createSpend(spendMapper.mapFromView(spendView), object : BaseFirebaseListener<Boolean>{
                override fun onLoading(isLoading: Boolean) {
                    loadingCreateSpend.postValue(isLoading)
                }
                override fun onSuccess(data: Boolean) {
                    successCreateSpend.postValue(data)
                }
                override fun onFailed(errorMessage: String) {
                    errorCreateSpend.postValue(errorMessage)
                }
            })
        }
    }
}