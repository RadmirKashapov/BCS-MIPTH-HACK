package com.bscskol.main.core.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.ApiKey
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.SecurityReference
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.time.LocalDateTime

@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Bean
    fun mainApi(): Docket =
            buildDocket(
                    Docket(DocumentationType.SWAGGER_2)
                            .groupName("mainApiV1")
                            .select()
                            .apis(RequestHandlerSelectors.basePackage("com.bscskol.main"))
            )

    @Bean
    fun adminApi(): Docket =
            buildDocket(
                    Docket(DocumentationType.SWAGGER_2)
                            .groupName("adminApiV1")
                            .select()
                            .apis(RequestHandlerSelectors.basePackage("digital.tutors.portal"))
            )

    private fun buildDocket(apiSelectorBuilder: ApiSelectorBuilder): Docket =
            apiSelectorBuilder
                    .build()
                    .securitySchemes(listOf(ApiKey("Bearer", "Authorization", "header")))
                    .pathMapping("/")
                    .apiInfo(ApiInfo.DEFAULT)
                    .forCodeGeneration(true)
                    .genericModelSubstitutes(ResponseEntity::class.java)
                    .securityContexts(listOf(securityContext()))
                    .directModelSubstitute(LocalDateTime::class.java, Long::class.java)

    private fun securityContext(): SecurityContext =
            SecurityContext
                    .builder()
                    .securityReferences(defaultAuth())
                    .forPaths(PathSelectors.any())
                    .build()

    private fun defaultAuth() =
            arrayListOf(
                    SecurityReference("Bearer", arrayOf(AuthorizationScope("global", "accessEverything")))
            )

}
