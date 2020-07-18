package com.anangkur.budgetku.data.mapper.auth

import com.anangkur.budgetku.data.mapper.Mapper
import com.anangkur.budgetku.data.model.auth.UserEntity
import com.anangkur.budgetku.domain.model.auth.User

class UserMapper: Mapper<UserEntity, User> {

    companion object{
        private var INSTANCE: UserMapper? = null
        fun getInstance() = INSTANCE
            ?: UserMapper()
    }

    override fun mapFromEntity(type: UserEntity): User {
        return User(
            userId = type.userId,
            name = type.name,
            firebaseToken = type.firebaseToken,
            weight = type.weight,
            email = type.email,
            height = type.height,
            photo = type.photo,
            providerName = type.providerName
        )
    }

    override fun mapToEntity(type: User): UserEntity {
        return UserEntity(
            userId = type.userId,
            name = type.name,
            firebaseToken = type.firebaseToken,
            weight = type.weight,
            email = type.email,
            height = type.height,
            photo = type.photo,
            providerName = type.providerName
        )
    }
}