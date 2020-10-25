package com.bscskol.main.user.entities

import com.bscskol.main.core.entities.BaseEntity
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
        var firstName: String? = null,
        var lastName: String? = null,
        var middleName: String? = null,

        @Indexed(unique = true)
        var email: String? = null,
        var password: String? = null,

        // TODO: Add enum for roles
        var role: Int = 0,
        var ratedArticles: MutableList<Rates> = mutableListOf(),
        var userLevel: UserLevel? = UserLevel.NEWBIE
) : BaseEntity()


data class Rates(
        var articleId: String?,
        var preference: Int = 0
)