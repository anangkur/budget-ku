package com.anangkur.budgetku.presentation.features.budget

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.model.budget.Spend
import com.anangkur.budgetku.domain.repository.BudgetRepository
import com.anangkur.budgetku.presentation.mapper.budget.SpendMapper
import com.anangkur.budgetku.presentation.model.budget.SpendView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailSpendViewModel(private val budgetRepository: BudgetRepository) : ViewModel() {

    private val spendMapper = SpendMapper.getInstance()

    var idProject = ""
    var idCategory: String? = null

    val loadingGetListSpend = MutableLiveData<Boolean>()
    val successGetListSpend = MutableLiveData<List<SpendView>>()
    val errorGetListSpend = MutableLiveData<String>()
    fun getListSpend(idProject: String, idCategory: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            budgetRepository.getListSpend(idProject, idCategory, object: BaseFirebaseListener<List<Spend>>{
                override fun onLoading(isLoading: Boolean) {
                    loadingGetListSpend.postValue(isLoading)
                }
                override fun onSuccess(data: List<Spend>) {
                    successGetListSpend.postValue(data.map { spendMapper.mapToView(it) })
                }
                override fun onFailed(errorMessage: String) {
                    errorGetListSpend.postValue(errorMessage)
                }
            })
        }
    }
}