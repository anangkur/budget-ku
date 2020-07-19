package com.anangkur.budgetku.utils

import com.anangkur.budgetku.remote.repository.BudgetRemoteRepository
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun getCreatedAt(): String {
        val date = Calendar.getInstance().time
        return SimpleDateFormat(BudgetRemoteRepository.DEFAULT_DATE_FORMAT, Locale.getDefault()).format(date)
    }
}