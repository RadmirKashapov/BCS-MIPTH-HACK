package com.bscskol.main.article.controllers

import com.bscskol.main.article.services.ArticleService
import com.bscskol.main.article.vo.ArticleDTO
import com.bscskol.main.article.vo.ArticleGetRq
import com.bscskol.main.article.vo.ArticlePreferenceRq
import com.bscskol.main.core.auth.AuthorizationService
import com.bscskol.main.core.controllers.BaseController
import com.bscskol.main.core.errors.EntityNotFoundException
import com.bscskol.main.core.vo.RestView
import com.fasterxml.jackson.annotation.JsonView
import io.swagger.v3.oas.annotations.parameters.RequestBody
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@RestController
@Validated
@RequestMapping("api/article")
class ArticleController : BaseController() {

    @Autowired
    lateinit var articleService: ArticleService

    @Autowired
    lateinit var authorizationService: AuthorizationService

    @GetMapping("all")
    fun getAll(
            @RequestParam(defaultValue = "50") page: Int
    ): ResponseEntity<Page<ArticleDTO>> = processServiceExceptions {
        try {
            val pageRequest = PageRequest.of(page, 10)
            ResponseEntity.ok(articleService.getAll(pageRequest))
        } catch (ex: EntityNotFoundException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Articles Not Found", ex)
        }
    }

    @PostMapping("all")
    fun getAll(
            @RequestParam(defaultValue = "50") page: Int,
            @RequestBody(required = false) @Valid articleGetRq: ArticleGetRq?
    ): ResponseEntity<Page<ArticleDTO>> = processServiceExceptions {
        try {
            val pageRequest = PageRequest.of(page, 10)
            ResponseEntity.ok(articleService.getAllByLevelAndCategoryPageable(articleGetRq, authorizationService.currentUserIdOrDie(), pageRequest))
        } catch (ex: EntityNotFoundException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Articles Not Found", ex)
        }
    }

    @PostMapping("{id}")
    fun getById(
            @PathVariable id: String
    ): ResponseEntity<ArticleDTO> = processServiceExceptions {
        ResponseEntity.ok(articleService.getByIdOrThrow(id, authorizationService.currentUserIdOrDie()))
    }

    @PostMapping("rate")
    fun rateArticle(
            @Valid @RequestBody(required = true) rate: ArticlePreferenceRq
    ): ResponseEntity<*> = processServiceExceptions {
        ResponseEntity.ok(articleService.rateArticle(rate, authorizationService.currentUserIdOrDie()))
    }

    @PostMapping("create")
    fun createArticle(
            @JsonView(RestView.Request::class)
            @Valid @RequestBody articleDTO: ArticleDTO
    ): ResponseEntity<ArticleDTO> = processServiceExceptions {
        ResponseEntity.ok(articleService.create(articleDTO))
    }

    @GetMapping("recommended")
    fun getRecommendedArticles(
            @RequestParam(defaultValue = "50") page: Int
    ): ResponseEntity<Page<ArticleDTO>> = processServiceExceptions {
        try {
            val pageRequest = PageRequest.of(page, 10)
            ResponseEntity.ok(articleService.getRecommended(authorizationService.currentUserIdOrDie(), pageRequest))
        } catch (ex: EntityNotFoundException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Articles Not Found", ex)
        }
    }

}