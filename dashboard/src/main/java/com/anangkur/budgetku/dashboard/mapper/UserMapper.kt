package com.anangkur.budgetku.dashboard.mapper

import com.anangkur.budgetku.dashboard.model.UserIntent
import com.anangkur.budgetku.mapper.Mapper
import com.anangkur.budgetku.presentation.model.UserView

class UserMapper: Mapper<UserIntent, UserView> {

    companion object{
        private var INSTANCE: UserMapper? = null
        fun getInstance() = INSTANCE ?: UserMapper()
    }

    override fun mapToIntent(type: UserView): UserIntent {
        return UserIntent(
            profilePhoto = type.photo,
            name = type.name
        )
    }

    override fun mapFromIntent(type: UserIntent): UserView {
        return UserView(
            name = type.name ?: "",
            photo = type.profilePhoto ?: ""
        )
    }
}