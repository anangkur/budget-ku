package com.anangkur.budgetku.budget.view.detailProject

import com.anangkur.budgetku.model.CategoryProjectIntent

interface DetailProjectActionListener {
    fun onClickAddSpend()
    fun onClickSpendCategory(data: CategoryProjectIntent)
    fun onClickCardSpend()
    fun onClickEditProject(idProject: String)
    fun onClickDeleteProject(idProject: String)
}