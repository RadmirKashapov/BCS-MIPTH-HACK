package com.bscskol.main.category.vo

import com.fasterxml.jackson.annotation.JsonView
import com.bscskol.main.core.entities.BaseEntity
import com.bscskol.main.core.vo.RestView

data class CategoryDTO(
        @JsonView(RestView.Request::class)
        var name: String? = null,

        @JsonView(RestView.Request::class)
        var link: String? = null
) : BaseEntity()
