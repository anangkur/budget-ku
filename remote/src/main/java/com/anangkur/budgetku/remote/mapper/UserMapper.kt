package com.anangkur.budgetku.remote.mapper

import com.anangkur.budgetku.data.model.UserEntity
import com.anangkur.budgetku.remote.model.auth.UserRemoteModel

class UserMapper: Mapper<UserRemoteModel, UserEntity> {

    companion object{
        private var INSTANCE: UserMapper? = null
        fun getInstance() = INSTANCE ?: UserMapper()
    }

    override fun mapFromRemote(type: UserRemoteModel): UserEntity {
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

    override fun mapToRemote(type: UserEntity): UserRemoteModel {
        return UserRemoteModel(
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