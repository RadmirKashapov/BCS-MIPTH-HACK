package com.bscskol.main.article.services

import com.bscskol.main.article.entities.Article
import com.bscskol.main.article.repositories.ArticleRepository
import com.bscskol.main.article.vo.ArticleDTO
import com.bscskol.main.article.vo.ArticleGetRq
import com.bscskol.main.article.vo.ArticlePreferenceRq
import com.bscskol.main.core.errors.EntityNotFoundException
import com.bscskol.main.core.services.BaseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import kotlin.jvm.Throws

interface ArticleService : BaseService<Article, ArticleRepository> {

    fun getAll(pageable: Pageable): Page<ArticleDTO>

    @Throws(EntityNotFoundException::class)
    fun getByIdOrThrow(articleId: String, userId: String): ArticleDTO

    fun getAllByLevelAndCategoryPageable(articleRq: ArticleGetRq?, userId: String, pageable: Pageable): Page<ArticleDTO>

    @Throws(EntityNotFoundException::class)
    fun rateArticle(articlePreferenceRq: ArticlePreferenceRq, userId: String)

    fun create(articleDTO: ArticleDTO): ArticleDTO

    fun getAllByIds(ids: List<String>, pageable: Pageable): Page<ArticleDTO>

    @Throws(EntityNotFoundException::class)
    fun getRecommended(userId: String, pageable: Pageable): Page<ArticleDTO>
}