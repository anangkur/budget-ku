package com.anangkur.budgetku.presentation.features.budget

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.model.budget.Category
import com.anangkur.budgetku.domain.repository.BudgetRepository
import com.anangkur.budgetku.presentation.mapper.budget.CategoryMapper
import com.anangkur.budgetku.presentation.model.budget.CategoryView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelectCategoryViewModel(private val budgetRepository: BudgetRepository) : ViewModel() {

    private val categoryMapper = CategoryMapper.getInstance()

    val loadingGetCategory = MutableLiveData<Boolean>()
    val successGetCategory = MutableLiveData<List<CategoryView>>()
    val errorGetCategory = MutableLiveData<String>()
    fun getCategory() {
        CoroutineScope(Dispatchers.IO).launch {
            budgetRepository.getCategory(object: BaseFirebaseListener<List<Category>>{
                override fun onLoading(isLoading: Boolean) {
                    loadingGetCategory.postValue(isLoading)
                }
                override fun onSuccess(data: List<Category>) {
                    successGetCategory.postValue(data.map { categoryMapper.mapToView(it) })
                }
                override fun onFailed(errorMessage: String) {
                    errorGetCategory.postValue(errorMessage)
                }
            })
        }
    }
}