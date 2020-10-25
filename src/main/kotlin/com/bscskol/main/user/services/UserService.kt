package com.bscskol.main.user.services

import com.bscskol.main.core.errors.EntityNotFoundException
import com.bscskol.main.core.services.BaseService
import com.bscskol.main.user.entities.User
import com.bscskol.main.user.repositories.UserRepository
import com.bscskol.main.user.vo.UserDTO

interface UserService : BaseService<User, UserRepository> {

    fun create(user: UserDTO): UserDTO

    @Throws(EntityNotFoundException::class)
    fun getUserByIdOrThrow(id: String): UserDTO

    @Throws(EntityNotFoundException::class)
    fun getUserByEmailOrThrow(email: String): User

    fun upsertUser(id: String, user: UserDTO): UserDTO

    fun getAllByRatedArticlesHasArticleId(articleId:String): List<User>

}

