package com.anangkur.budgetku.auth.mapper

import com.anangkur.budgetku.auth.model.UserIntent
import com.anangkur.budgetku.mapper.Mapper
import com.anangkur.budgetku.presentation.model.auth.UserView

class UserMapper: Mapper<UserIntent, UserView> {

    companion object{
        private var INSTANCE: UserMapper? = null
        fun getInstance() = INSTANCE ?: UserMapper()
    }

    override fun mapToIntent(type: UserView): UserIntent {
        return UserIntent(
            userId = type.userId,
            name = type.name,
            firebaseToken = type.firebaseToken,
            email = type.email,
            photo = type.photo,
            providerName = type.providerName
        )
    }

    override fun mapFromIntent(type: UserIntent): UserView {
        return UserView(
            userId = type.userId,
            name = type.name,
            firebaseToken = type.firebaseToken,
            email = type.email,
            photo = type.photo,
            providerName = type.providerName
        )
    }
}