package com.anangkur.budgetku.presentation.features.budget

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.domain.BaseFirebaseListener
import com.anangkur.budgetku.domain.repository.BudgetRepository
import com.anangkur.budgetku.presentation.mapper.budget.CategoryProjectMapper
import com.anangkur.budgetku.presentation.mapper.budget.SpendMapper
import com.anangkur.budgetku.presentation.model.budget.CategoryProjectView
import com.anangkur.budgetku.presentation.model.budget.SpendView
import com.anangkur.budgetku.presentation.model.dashboard.ProjectView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailProjectViewModel(private val budgetRepository: BudgetRepository) : ViewModel() {

    private val spendMapper = SpendMapper.getInstance()
    private val categoryProjectMapper = CategoryProjectMapper.getInstance()

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

    private val projectInternalSetter = MutableLiveData<ProjectView>()
    val projectPublicObserver: LiveData<ProjectView> = projectInternalSetter
    fun setProjectDetail(data: ProjectView) {
        projectInternalSetter.postValue(data)
        getListCategory(data.listCategory)
    }

    val loadingCreateSpend = MutableLiveData<Boolean>()
    val successCreateSpend = MutableLiveData<SpendView>()
    val errorCreateSpend = MutableLiveData<String>()
    fun createSpend(spendView: SpendView) {
        CoroutineScope(Dispatchers.IO).launch {
            budgetRepository.createSpend(spendMapper.mapFromView(spendView), object : BaseFirebaseListener<Boolean>{
                override fun onLoading(isLoading: Boolean) {
                    loadingCreateSpend.postValue(isLoading)
                }
                override fun onSuccess(data: Boolean) {
                    setProjectDetail(projectInternalSetter.value!!.apply {
                        this.listCategory = setCategorySelectedPosition(
                            countRemaining(
                                spendView,
                                categorySelectedValue!!
                            ),
                            listCategory, categorySelectedPosition
                        )
                    })
                    successCreateSpend.postValue(spendView)
                }
                override fun onFailed(errorMessage: String) {
                    errorCreateSpend.postValue(errorMessage)
                }
            })
        }
    }

    val loadingEditProject = MutableLiveData<Boolean>()
    val successEditProject = MutableLiveData<Boolean>()
    val errorEditProject = MutableLiveData<String>()
    fun editProject(projectView: ProjectView) {
        budgetRepository.createProject(
            idProject = projectView.id,
            title = projectView.title,
            startDate = projectView.startDate,
            endDate = projectView.endDate,
            category = projectView.listCategory.map { categoryProjectMapper.mapFromView(it) },
            listener = object : BaseFirebaseListener<Boolean> {
                override fun onLoading(isLoading: Boolean) {
                    loadingEditProject.postValue(isLoading)
                }
                override fun onSuccess(data: Boolean) {
                    successEditProject.postValue(data)
                }
                override fun onFailed(errorMessage: String) {
                    errorEditProject.postValue(errorMessage)
                }
            }
        )
    }

    private fun setCategorySelectedPosition(
        category: CategoryProjectView,
        listCategory: List<CategoryProjectView>,
        position: Int
    ): List<CategoryProjectView> {
        val arrayListCategory = ArrayList<CategoryProjectView>()
        arrayListCategory.addAll(listCategory)
        arrayListCategory[position] = category
        return arrayListCategory
    }

    private fun countRemaining(spendView: SpendView, categoryProjectView: CategoryProjectView): CategoryProjectView {
        return categoryProjectView.apply {
            spend += spendView.spend
        }
    }
}