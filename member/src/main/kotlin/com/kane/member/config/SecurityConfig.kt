package com.kane.member.config

import com.kane.member.service.oauth2.CustomOAuth2UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val customOAuth2UserService: CustomOAuth2UserService
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .anonymous { it.disable() }
            .oauth2Login { it ->
                it.userInfoEndpoint {
                    it.userService(customOAuth2UserService)
                }
            }
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/member/login",
                    "/callback/oauth2/code/google",
                ).permitAll()
                it.requestMatchers(
                    "/member/oauth2/authorization/google",
                    ).authenticated()

            }

        return http.build()

    }
}