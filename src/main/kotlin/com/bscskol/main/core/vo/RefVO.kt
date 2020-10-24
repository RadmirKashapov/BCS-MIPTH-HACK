package com.bscskol.main.core.vo

import com.fasterxml.jackson.annotation.JsonView

class RefVO {
    @JsonView(RestView.Request::class)
    var id: String? = null
}
