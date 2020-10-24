package com.bscskol.main.core.vo

import java.util.*

open class BaseDTO {
    var id: String? = null
    var createdDt: Date? = Date(0)
    var modifiedDt: Date? = Date(0)
}
