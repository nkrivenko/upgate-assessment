package com.nkrivenko.upgate.upgateintegration.integration

import com.nkrivenko.upgate.upgateintegration.dto.SaleRequest
import com.nkrivenko.upgate.upgateintegration.dto.SaleResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitEntity
import org.springframework.web.reactive.function.client.awaitExchange
import java.util.UUID

interface UpgateClient {
    suspend fun sale(saleRequest: SaleRequest): SaleResponse
}

@Component
class UpgateClientImpl(
    private val upgateWebClient: WebClient,
) : UpgateClient {

    companion object {
        const val X_IDEMPOTENCY_KEY_HEADER = "X-Idempotency-Key"
        private val LOGGER = LoggerFactory.getLogger(UpgateClientImpl::class.java)
    }

    override suspend fun sale(saleRequest: SaleRequest): SaleResponse {
        val result = upgateWebClient.post()
            .uri { it.pathSegment("sale").build() }
            .header(X_IDEMPOTENCY_KEY_HEADER, UUID.randomUUID().toString())
            .body(BodyInserters.fromValue(saleRequest))
            .awaitExchange {
                it.awaitEntity<SaleResponse>()
            }

        val statusCode = result.statusCode
        if (statusCode.isError) {
            LOGGER.warn("{}", result.body!!)
        }

        return result.body!!
    }

}
