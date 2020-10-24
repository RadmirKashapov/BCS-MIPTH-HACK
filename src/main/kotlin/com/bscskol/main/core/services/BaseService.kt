package com.bscskol.main.core.services

import com.bscskol.main.core.entities.BaseEntity
import com.bscskol.main.core.errors.EntityNotFoundException
import com.bscskol.main.core.repositories.BaseRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface BaseService<T : BaseEntity, R : BaseRepository<T>> {

    fun findAll(): MutableIterable<T>

    fun findAllPageable(pageable: Pageable): Page<T>

    fun save(entity: T): T

    fun findById(id: String): Optional<T>

    fun findByIds(ids: List<String>): List<T>

    fun deleteById(id: String)

    @Throws(EntityNotFoundException::class)
    fun findByIdOrThrow(id: String): T

    fun deleteAll()

    fun countAll(): Long

}
