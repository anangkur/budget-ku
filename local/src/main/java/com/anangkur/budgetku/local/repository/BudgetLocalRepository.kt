package com.anangkur.budgetku.local.repository

import android.content.Context
import android.content.SharedPreferences
import com.anangkur.budgetku.data.repository.budget.BudgetLocal
import com.anangkur.budgetku.local.Const

class BudgetLocalRepository(
    private val preferences: SharedPreferences
): BudgetLocal {
    companion object{
        private var INSTANCE: BudgetLocalRepository? = null
        fun getInstance(context: Context) = INSTANCE
            ?: BudgetLocalRepository(
                context.getSharedPreferences(
                    Const.PREF_NAME,
                    Context.MODE_PRIVATE
                )
            )
    }
}