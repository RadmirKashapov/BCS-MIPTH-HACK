package com.bscskol.main.user.repositories

import com.bscskol.main.core.repositories.BaseRepository
import com.bscskol.main.user.entities.User
import org.springframework.data.mongodb.repository.Query

interface UserRepository : BaseRepository<User> {

    fun findByEmail(email: String): User?

    @Query(value = "db.users.find()")
    fun findAllByRatedArticlesHasArticleId(articleId:String): List<User>

}
