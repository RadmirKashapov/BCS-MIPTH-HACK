package com.bscskol.main.category.controllers

import com.fasterxml.jackson.annotation.JsonView
import com.bscskol.main.category.services.CategoryService
import com.bscskol.main.category.vo.CategoryDTO
import com.bscskol.main.core.controllers.BaseController
import com.bscskol.main.core.errors.EntityNotFoundException
import com.bscskol.main.core.vo.RestView
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
@RequestMapping("api/category")
class CategoryController : BaseController() {

    @Autowired
    lateinit var categoryService: CategoryService

    @PostMapping("create")
    fun createCategory(
            @JsonView(RestView.Request::class)
            @Valid @RequestBody categoryDTO: CategoryDTO
    ): ResponseEntity<CategoryDTO> = processServiceExceptions {
        ResponseEntity.ok(categoryService.create(categoryDTO))
    }

    @GetMapping("{id}")
    fun getByIdOrThrow(
            @PathVariable id: String
    ): ResponseEntity<CategoryDTO> = processServiceExceptions {
        ResponseEntity.ok(categoryService.getCategoryById(id))
    }

    @PostMapping("all/ids")
    fun getByIds(
            @RequestBody ids: List<String>,
            @RequestParam(defaultValue = "50") page: Int,
    ): ResponseEntity<Page<CategoryDTO>> = processServiceExceptions {
        try {
            val pageRequest = PageRequest.of(page, 10)
            ResponseEntity.ok(categoryService.getCategoriesByIds(ids, pageRequest))
        } catch (ex: EntityNotFoundException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Categories Not Found", ex)
        }
    }

}
