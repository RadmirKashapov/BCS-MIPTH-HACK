package com.bscskol.main.article.vo

import com.bscskol.main.core.vo.RestView
import com.bscskol.main.user.entities.UserLevel
import com.fasterxml.jackson.annotation.JsonView

class ArticleGetRq {
    @JsonView(RestView.Request::class)
    var userLevel: UserLevel? = null

    @JsonView(RestView.Request::class)
    var categories: List<String>? = listOf()
}