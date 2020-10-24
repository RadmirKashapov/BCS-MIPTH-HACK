package com.bscskol.main.article.vo

import com.bscskol.main.core.vo.RestView
import com.bscskol.main.user.entities.UserPreference
import com.fasterxml.jackson.annotation.JsonView

class ArticlePreferenceRq {
    @JsonView(RestView.Request::class)
    var preference: UserPreference? = UserPreference.NONE

    @JsonView(RestView.Request::class)
    var articleId: String? = null
}