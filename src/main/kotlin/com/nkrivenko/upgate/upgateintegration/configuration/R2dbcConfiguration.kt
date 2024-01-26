package com.nkrivenko.upgate.upgateintegration.configuration

import com.nkrivenko.upgate.upgateintegration.domain.Payment
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.ReactiveAuditorAware
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback
import reactor.core.publisher.Mono
import java.util.UUID

@Configuration
@EnableR2dbcAuditing
class R2dbcConfiguration {
    @Bean
    fun beforeSaveCallback(): BeforeConvertCallback<Payment> {
        return BeforeConvertCallback<Payment> { entity, _ ->
            if (entity.id == null) {
                entity.id = UUID.randomUUID().toString()
            }
            Mono.just(entity)
        }
    }

    @Bean
    fun auditorAware(): ReactiveAuditorAware<String?> {
        return ReactiveAuditorAware<String?> { Mono.empty() }
    }
}