package com.bscskol.main.article.services.impl

import com.bscskol.main.article.entities.Article
import com.bscskol.main.article.mappers.ArticleMapper
import com.bscskol.main.article.mappers.ArticleMapperImpl
import com.bscskol.main.article.repositories.ArticleRepository
import com.bscskol.main.article.services.ArticleService
import com.bscskol.main.article.vo.ArticleDTO
import com.bscskol.main.article.vo.ArticleGetRq
import com.bscskol.main.article.vo.ArticlePreferenceRq
import com.bscskol.main.core.errors.EntityNotFoundException
import com.bscskol.main.core.services.impl.BaseServiceImpl
import com.bscskol.main.user.entities.Rates
import com.bscskol.main.user.entities.UserLevel
import com.bscskol.main.user.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class ArticleServiceImpl(
        private val mapper: ArticleMapper = ArticleMapperImpl()
) : BaseServiceImpl<Article, ArticleRepository>(), ArticleService {

    @Autowired
    lateinit var userService: UserService

    override fun create(articleDTO: ArticleDTO): ArticleDTO {
        return save(mapper.convertToModel(articleDTO)).let(mapper::convertToDto)
    }

    override fun getAll(pageable: Pageable): Page<ArticleDTO> {
        return repository.findAll(pageable).map(mapper::convertToDto)
    }

    @Throws(EntityNotFoundException::class)
    override fun getByIdOrThrow(articleId: String, userId: String): ArticleDTO {
        val user = userService.findByIdOrThrow(userId)
        return mapper.convertToDto(findByIdOrThrow(articleId))
    }

    override fun getAllByLevelAndCategoryPageable(articleRq: ArticleGetRq?, userId: String, pageable: Pageable): Page<ArticleDTO> {
        val user = userService.findByIdOrThrow(userId)

        return when (articleRq) {
            null -> {
                repository.findAllByRequiredLevel(UserLevel.NEWBIE, pageable).map(mapper::convertToDto)
            }
            else -> {
                repository.findAllByCategoriesContainsAndRequiredLevel(articleRq.categories!!, articleRq.userLevel!!, pageable).map(mapper::convertToDto)
            }
        }

    }

    override fun rateArticle(articlePreferenceRq: ArticlePreferenceRq, userId: String) {
        val user = userService.findByIdOrThrow(userId)
        userService.save(user.apply {
            this.ratedArticles.plus(Rates(articleId = findByIdOrThrow(articlePreferenceRq.articleId!!).id, preference = articlePreferenceRq.preference))
        })
    }


}