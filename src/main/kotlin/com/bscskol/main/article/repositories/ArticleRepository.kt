package com.bscskol.main.article.repositories

import com.bscskol.main.article.entities.Article
import com.bscskol.main.core.repositories.BaseRepository
import com.bscskol.main.user.entities.UserLevel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ArticleRepository : BaseRepository<Article> {
    fun findAllByCategoriesContainsAndRequiredLevel(categories: List<String>, requiredLevel: UserLevel, pageable: Pageable): Page<Article>
    fun findAllByRequiredLevel(requiredLevel: UserLevel, pageable: Pageable): Page<Article>
    fun findAllByIdIn(ids: List<String>, pageable: Pageable): Page<Article>
}