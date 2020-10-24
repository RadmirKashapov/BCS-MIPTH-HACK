package com.bscskol.main.article.mappers

import com.bscskol.main.article.entities.Article
import com.bscskol.main.article.vo.ArticleDTO
import com.bscskol.main.core.mapper.BaseMapper
import org.mapstruct.Mapper
import org.mapstruct.NullValueCheckStrategy
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface ArticleMapper : BaseMapper<Article, ArticleDTO>