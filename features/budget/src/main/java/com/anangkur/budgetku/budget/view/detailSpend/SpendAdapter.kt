package com.anangkur.budgetku.budget.view.detailSpend

import android.view.LayoutInflater
import android.view.ViewGroup
import com.anangkur.budgetku.base.BaseAdapter
import com.anangkur.budgetku.budget.databinding.ItemSpendBinding
import com.anangkur.budgetku.budget.model.SpendUiModel
import com.anangkur.budgetku.utils.currencyFormatToRupiah
import com.anangkur.budgetku.utils.setImageUrl

class SpendAdapter : BaseAdapter<ItemSpendBinding, SpendUiModel>() {
    override fun bindView(parent: ViewGroup): ItemSpendBinding {
        return ItemSpendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun bind(data: SpendUiModel, itemView: ItemSpendBinding, position: Int) {
        itemView.apply {
            ivSpend.setImageUrl(data.image)
            tvSpendDate.text = data.date
            tvSpendCategory.text = data.title
            tvSpend.text = data.spend.toDouble().currencyFormatToRupiah()
        }
    }
}