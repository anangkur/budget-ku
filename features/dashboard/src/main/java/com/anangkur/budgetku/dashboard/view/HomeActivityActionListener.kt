package com.anangkur.budgetku.dashboard.view

import com.anangkur.budgetku.dashboard.model.ProjectIntent

interface HomeActivityActionListener {
    fun onClickAddProject()
    fun onClickEditProfile()
    fun onClickItem(data: ProjectIntent)
}