package com.nkrivenko.upgate.upgateintegration.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class WebFluxConfiguration {

    @Bean
    fun indexRouter(
        @Value("classpath:/public/index.html") indexHtml: Resource,
        @Value("classpath:/public/success.html") successHtml: Resource,
        @Value("classpath:/public/failure.html") failureHtml: Resource,
    ): RouterFunction<ServerResponse> {
        return router {
            GET("/index.html") {
                ok().contentType(MediaType.TEXT_HTML).bodyValue(indexHtml)
            }
            GET("/success.html") {
                ok().contentType(MediaType.TEXT_HTML).bodyValue(successHtml)
            }
            GET("/failure.html") {
                ok().contentType(MediaType.TEXT_HTML).bodyValue(failureHtml)
            }
            GET("/favicon.ico") {
                noContent().build()
            }
        }
    }

}