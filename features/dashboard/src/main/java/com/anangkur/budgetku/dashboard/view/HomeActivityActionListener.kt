package com.anangkur.budgetku.dashboard.view

import com.anangkur.budgetku.model.ProjectIntent

interface HomeActivityActionListener {
    fun onClickAddProject()
    fun onClickEditProfile()
    fun onClickItem(data: ProjectIntent)
}