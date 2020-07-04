package com.anangkur.budgetku.budget.mapper

import com.anangkur.budgetku.budget.model.SpendCategoryUiModel
import com.anangkur.budgetku.presentation.model.SpendCategoryView

class SpendCategoryMapper : Mapper<SpendCategoryUiModel, SpendCategoryView> {

    companion object{
        private var INSTANCE: SpendCategoryMapper? = null
        fun getInstance() = INSTANCE ?: SpendCategoryMapper()
    }

    override fun mapFromUiModel(data: SpendCategoryUiModel): SpendCategoryView {
        return SpendCategoryView(
            image = data.image,
            title = data.title,
            remaining = data.remaining
        )
    }

    override fun mapToUiModel(data: SpendCategoryView): SpendCategoryUiModel {
        return SpendCategoryUiModel(
            image = data.image,
            remaining = data.remaining,
            title = data.title
        )
    }
}