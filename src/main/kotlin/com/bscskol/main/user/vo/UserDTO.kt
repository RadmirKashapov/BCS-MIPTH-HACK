package com.bscskol.main.user.vo

import com.fasterxml.jackson.annotation.JsonView
import com.bscskol.main.core.vo.BaseDTO
import com.bscskol.main.core.vo.RestView
import com.bscskol.main.user.entities.Rates
import com.bscskol.main.user.entities.UserLevel
import javax.validation.constraints.Email

data class UserDTO(
        @JsonView(value = [RestView.Request::class])
        var firstName: String? = null,

        @JsonView(value = [RestView.Request::class])
        var lastName: String? = null,

        @JsonView(value = [RestView.Request::class])
        var middleName: String? = null,

        @Email
        var email: String? = null,

        var ratedArticles: MutableList<Rates> = mutableListOf(),

        @JsonView(value = [RestView.Request::class])
        var userLevel: UserLevel? = UserLevel.NEWBIE

) : BaseDTO()
