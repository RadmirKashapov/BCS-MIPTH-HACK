package com.bscskol.main.core.repositories

import com.bscskol.main.core.entities.BaseEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface BaseRepository<T : BaseEntity> : MongoRepository<T, String>
