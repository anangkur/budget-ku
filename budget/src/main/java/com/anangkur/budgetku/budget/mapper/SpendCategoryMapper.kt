package com.anangkur.budgetku.budget.mapper

import com.anangkur.budgetku.budget.model.SpendCategoryUiModel
import com.anangkur.budgetku.mapper.Mapper
import com.anangkur.budgetku.presentation.model.budget.SpendCategoryView

class SpendCategoryMapper : Mapper<SpendCategoryUiModel, SpendCategoryView> {

    companion object{
        private var INSTANCE: SpendCategoryMapper? = null
        fun getInstance() = INSTANCE ?: SpendCategoryMapper()
    }

    override fun mapFromIntent(type: SpendCategoryUiModel): SpendCategoryView {
        return SpendCategoryView(
            image = type.image,
            title = type.title,
            remaining = type.remaining
        )
    }

    override fun mapToIntent(type: SpendCategoryView): SpendCategoryUiModel {
        return SpendCategoryUiModel(
            image = type.image,
            remaining = type.remaining,
            title = type.title
        )
    }
}