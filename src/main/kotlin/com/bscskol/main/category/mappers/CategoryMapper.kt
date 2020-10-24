package com.bscskol.main.category.mappers

import com.bscskol.main.category.entities.Category
import com.bscskol.main.category.vo.CategoryDTO
import com.bscskol.main.core.mapper.BaseMapper
import org.mapstruct.*

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface CategoryMapper : BaseMapper<Category, CategoryDTO>
