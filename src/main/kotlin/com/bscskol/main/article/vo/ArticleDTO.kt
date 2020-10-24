package com.bscskol.main.article.vo

import com.bscskol.main.article.entities.Rates
import com.bscskol.main.core.vo.BaseDTO
import com.bscskol.main.core.vo.RestView
import com.bscskol.main.user.entities.UserLevel
import com.fasterxml.jackson.annotation.JsonView
import java.sql.Timestamp

data class ArticleDTO(
        @JsonView(RestView.Request::class)
        var description: String? = null,

        @JsonView(RestView.Request::class)
        var categories: List<String>? = listOf(),

        @JsonView(RestView.Request::class)
        var requiredLevel: UserLevel? = UserLevel.NEWBIE,

        @JsonView(RestView.Request::class)
        var content: String? = null,

        @JsonView(RestView.Request::class)
        var rubric: String? = null,

        @JsonView(RestView.Request::class)
        var topic: String? = null,

        @JsonView(RestView.Request::class)
        var articleLink: String? = null,

        @JsonView(RestView.Request::class)
        var dateOfPublication: Timestamp = Timestamp(0),

        @JsonView(RestView.Request::class)
        var imgSrc: String? = null,

        var rates: Set<Rates> = setOf()
) : BaseDTO()