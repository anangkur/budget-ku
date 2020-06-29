package com.anangkur.budgetku.dashboard.view

import com.anangkur.budgetku.dashboard.model.ItemProjectIntent

interface HomeActivityActionListener {
    fun onClickAddProject()
    fun onClickEditProfile()
    fun onClickItem(data: ItemProjectIntent)
}