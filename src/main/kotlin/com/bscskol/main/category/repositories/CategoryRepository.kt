package com.bscskol.main.category.repositories

import com.bscskol.main.category.entities.Category
import com.bscskol.main.core.repositories.BaseRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CategoryRepository : BaseRepository<Category> {
    fun findAllByIdIn(ids: List<String>, pageable: Pageable): Page<Category>
}
