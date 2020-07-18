package com.anangkur.budgetku.budget.view.addProject

interface AddProjectActionListener {
    fun onClickAddCategory()
    fun onClickSave()
    fun onClickDate(type: Int)
    fun onClickDeleteCategory(position: Int)
}