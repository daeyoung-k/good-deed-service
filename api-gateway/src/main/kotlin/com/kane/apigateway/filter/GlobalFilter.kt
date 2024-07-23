package com.kane.apigateway.filter

import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GlobalFilter {

    @Bean
    fun customGlobalFilter(): GlobalFilter {
        return GlobalFilter { exchange, chain ->

            println("글로벌 필터 로그다 이자식아")
            val modifiedExchange = exchange.mutate()
                .request { request ->
                    request.headers { headers ->
                        headers.add("apigateway", "hello gateway")
                    }
                }
                .build()
            chain.filter(modifiedExchange)
        }
    }
}