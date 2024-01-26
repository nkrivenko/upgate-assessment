package com.nkrivenko.upgate.upgateintegration

import com.nkrivenko.upgate.upgateintegration.configuration.properties.UpgateProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(UpgateProperties::class)
class UpgateIntegrationApplication

fun main(args: Array<String>) {
    runApplication<UpgateIntegrationApplication>(*args)
}
