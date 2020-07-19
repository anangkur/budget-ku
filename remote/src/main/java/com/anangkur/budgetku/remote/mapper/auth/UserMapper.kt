package com.anangkur.budgetku.remote.mapper.auth

import com.anangkur.budgetku.data.model.auth.UserEntity
import com.anangkur.budgetku.remote.mapper.Mapper
import com.anangkur.budgetku.remote.model.auth.UserRemote

class UserMapper:
    Mapper<UserRemote, UserEntity> {

    companion object{
        private var INSTANCE: UserMapper? = null
        fun getInstance() = INSTANCE
            ?: UserMapper()
    }

    override fun mapFromRemote(type: UserRemote): UserEntity {
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

    override fun mapToRemote(type: UserEntity): UserRemote {
        return UserRemote(
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