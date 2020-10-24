package com.bscskol.main.core.entities

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import java.util.*

open class BaseEntity {

    @Id
    var id: String? = null

    @CreatedDate
    var createdDt: LocalDateTime? = LocalDateTime.now()

    @LastModifiedDate
    var modifiedDt: Date? = Date(0)

}
