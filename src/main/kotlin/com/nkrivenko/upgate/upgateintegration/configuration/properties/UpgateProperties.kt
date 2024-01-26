package com.nkrivenko.upgate.upgateintegration.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "application.upgate")
data class UpgateProperties(
    val apiKey: String,
    val baseUrl: String,
    val successUrl: String,
    val failureUrl: String,
)
