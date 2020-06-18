package com.anangkur.budgetku.data.mapper

import com.anangkur.budgetku.data.model.UserEntity
import com.anangkur.budgetku.domain.model.User

class UserMapper: Mapper<UserEntity, User> {

    companion object{
        private var INSTANCE: UserMapper? = null
        fun getInstance() = INSTANCE ?: UserMapper()
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