package com.bscskol.main.article.entities

import com.bscskol.main.core.entities.BaseEntity
import com.bscskol.main.user.entities.UserLevel
import com.bscskol.main.user.entities.UserPreference
import org.springframework.data.mongodb.core.mapping.Document
import java.sql.Timestamp

@Document
data class Article(
        var description: String? = null,
        var categories: List<String>? = listOf(),
        var requiredLevel: UserLevel? = UserLevel.NEWBIE,
        var content: String? = null,
        var rubric: String? = null,
        var topic: String? = null,
        var articleLink: String? = null,
        var dateOfPublication: Timestamp = Timestamp(0),
        var imgSrc: String? = null,
        var rates: Set<Rates> = setOf()
): BaseEntity()

data class Rates(
        var userId: String?,
        var preference: Int = 0
)