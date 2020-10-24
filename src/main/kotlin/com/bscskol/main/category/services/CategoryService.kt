package com.bscskol.main.category.services

import com.bscskol.main.category.entities.Category
import com.bscskol.main.category.repositories.CategoryRepository
import com.bscskol.main.category.vo.CategoryDTO
import com.bscskol.main.core.services.BaseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CategoryService : BaseService<Category, CategoryRepository> {

    fun create(categoryDTO: CategoryDTO): CategoryDTO
    fun getCategoryById(id: String): CategoryDTO
    fun getCategoriesByIds(ids: List<String>, pageable: Pageable): Page<CategoryDTO>

}
