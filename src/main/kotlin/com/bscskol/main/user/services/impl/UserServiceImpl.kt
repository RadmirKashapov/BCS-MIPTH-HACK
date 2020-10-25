package com.bscskol.main.user.services.impl

import com.bscskol.main.core.errors.EntityNotFoundException
import com.bscskol.main.core.services.impl.BaseServiceImpl
import com.bscskol.main.user.entities.User
import com.bscskol.main.user.mappers.UserMapper
import com.bscskol.main.user.mappers.UserMapperImpl
import com.bscskol.main.user.repositories.UserRepository
import com.bscskol.main.user.services.UserService
import com.bscskol.main.user.vo.UserDTO
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
        private val userMapper: UserMapper = UserMapperImpl()
) : BaseServiceImpl<User, UserRepository>(), UserService {

    override fun create(user: UserDTO): UserDTO =
            userMapper.convertToDto(save(userMapper.convertToModel(user)))

    override fun getUserByIdOrThrow(id: String): UserDTO =
            userMapper.convertToDto(findByIdOrThrow(id))

    @Throws(EntityNotFoundException::class)
    override fun getUserByEmailOrThrow(email: String): User =
            repository.findByEmail(email)
                    ?: throw EntityNotFoundException("User with $email not found.")

    override fun upsertUser(id: String, user: UserDTO): UserDTO {
        val entity = findByIdOrThrow(id).let { userMapper.updateModelFromDto(user, it) }

        val id = save(entity).id
                ?: throw IllegalArgumentException("Bad id returned.")

        return getUserByIdOrThrow(id)
    }

    override fun getAllByRatedArticlesHasArticleId(articleId: String): List<User> {
        return repository.findAllByRatedArticlesHasArticleId(articleId)
    }

}
