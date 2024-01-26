package com.nkrivenko.upgate.upgateintegration.configuration

import com.nkrivenko.upgate.upgateintegration.configuration.properties.UpgateProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration

const val X_API_KEY_HEADER = "X-Api-Key"

@Configuration
class PaymentGateConfiguration {
    @Bean
    fun upgateWebClient(properties: UpgateProperties): WebClient {
        val client = HttpClient.create().responseTimeout(Duration.ofSeconds(10))

        return WebClient.builder()
            .baseUrl(properties.baseUrl)
            .clientConnector(ReactorClientHttpConnector(client))
            .defaultHeaders {
                it[X_API_KEY_HEADER] = properties.apiKey
                it[HttpHeaders.CONTENT_TYPE] = MediaType.APPLICATION_JSON_VALUE
            }.build()
    }
}