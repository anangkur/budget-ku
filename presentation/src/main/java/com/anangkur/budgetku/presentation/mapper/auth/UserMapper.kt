package com.anangkur.budgetku.presentation.mapper.auth

import com.anangkur.budgetku.domain.model.auth.User
import com.anangkur.budgetku.presentation.mapper.Mapper
import com.anangkur.budgetku.presentation.model.auth.UserView

class UserMapper:
    Mapper<UserView, User> {

    companion object{
        private var INSTANCE: UserMapper? = null
        fun getInstance() = INSTANCE
            ?: UserMapper()
    }

    override fun mapToView(type: User): UserView {
        return UserView(
            userId = type.userId,
            name = type.name,
            firebaseToken = type.firebaseToken,
            email = type.email,
            photo = type.photo,
            providerName = type.providerName
        )
    }

    override fun mapFromView(type: UserView): User {
        return User(
            userId = type.userId,
            name = type.name,
            firebaseToken = type.firebaseToken,
            email = type.email,
            photo = type.photo,
            providerName = type.providerName
        )
    }
}