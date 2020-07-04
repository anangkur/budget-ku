package com.anangkur.budgetku.presentation.features.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.budgetku.presentation.model.dashboard.ItemProjectView
import com.anangkur.budgetku.presentation.model.auth.UserView

class HomeViewModel: ViewModel() {
    private val listProjectInternalSetter = MutableLiveData<List<ItemProjectView>>()
    val listProjectPublicObserver: LiveData<List<ItemProjectView>> = listProjectInternalSetter
    fun createDummyListProject() {
        val listProject = ArrayList<ItemProjectView>()
        for (i in 0 until 10) {
            listProject.add(
                ItemProjectView(
                    title = "Dummy Project",
                    period = "20 Jun 2020 - 20 Jul 2020",
                    progress = 50,
                    spendPercentage = "Spend 50%"
                )
            )
        }
        listProjectInternalSetter.postValue(listProject)
    }

    private val userInternalSetter = MutableLiveData<UserView>()
    val userPublicObserver: LiveData<UserView> = userInternalSetter
    fun createDummyUser() {
        val user = UserView(
            userId = "0",
            name = "Anang Kur",
            email = "anangk97@gmail.com",
            firebaseToken = "",
            photo = "https://lh3.googleusercontent.com/a-/AOh14Gj4pSeYhgSOZRV2Nf0KdnK5JRHW06TGZvmlHLmn=s96-c",
            providerName = ""
        )
        userInternalSetter.postValue(user)
    }
}