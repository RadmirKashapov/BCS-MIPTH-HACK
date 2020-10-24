package com.bscskol.main.core.services.impl

import com.bscskol.main.core.entities.BaseEntity
import com.bscskol.main.core.errors.EntityNotFoundException
import com.bscskol.main.core.repositories.BaseRepository
import com.bscskol.main.core.services.BaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
abstract class BaseServiceImpl<T : BaseEntity, R : BaseRepository<T>> : BaseService<T, R> {

    @Autowired
    lateinit var repository: R

    override fun findAll(): MutableIterable<T> = repository.findAll()

    override fun findAllPageable(pageable: Pageable): Page<T> = repository.findAll(pageable)

    override fun save(entity: T): T = repository.save(entity)

    override fun deleteAll() = repository.deleteAll()

    override fun findById(id: String): Optional<T> = repository.findById(id)

    override fun findByIds(ids: List<String>): List<T> = ids.map(this::findByIdOrThrow)

    override fun deleteById(id: String) = repository.deleteById(id)

    @Throws(EntityNotFoundException::class)
    override fun findByIdOrThrow(id: String): T =
            findById(id).orElseThrow { EntityNotFoundException("Entity $id not found.") }

    override fun countAll(): Long = repository.count()

}
