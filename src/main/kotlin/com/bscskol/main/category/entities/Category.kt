package com.bscskol.main.category.entities

import com.bscskol.main.core.entities.BaseEntity

data class Category(
        var name: String? = null,
        var link: String? = null
) : BaseEntity()
