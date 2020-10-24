package com.bscskol.main.category.services.impl

import com.bscskol.main.category.entities.Category
import com.bscskol.main.category.mappers.CategoryMapper
import com.bscskol.main.category.mappers.CategoryMapperImpl
import com.bscskol.main.category.repositories.CategoryRepository
import com.bscskol.main.category.services.CategoryService
import com.bscskol.main.category.vo.CategoryDTO
import com.bscskol.main.core.services.impl.BaseServiceImpl
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(
        private val categoryMapper: CategoryMapper = CategoryMapperImpl()
) : CategoryService, BaseServiceImpl<Category, CategoryRepository>() {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun create(categoryDTO: CategoryDTO): CategoryDTO =
        save(categoryMapper.convertToModel(categoryDTO)).let(categoryMapper::convertToDto)

    override fun getCategoryById(id: String): CategoryDTO {
        return findByIdOrThrow(id).let(categoryMapper::convertToDto)
    }

    override fun getCategoriesByIds(ids: List<String>, pageable: Pageable): Page<CategoryDTO> {
        return repository.findAllByIdIn(ids, pageable).map(categoryMapper::convertToDto)
    }

}
