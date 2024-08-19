package com.kane.member.config

import com.kane.member.service.oauth2.CustomOAuth2SuccessHandler
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
    private val customOAuth2UserService: CustomOAuth2UserService,
    private val customOAuth2SuccessHandler: CustomOAuth2SuccessHandler
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .anonymous { it.disable() }
            .oauth2Login { oauth2 ->
                oauth2.userInfoEndpoint {
                    it.userService(customOAuth2UserService)
                }
                oauth2.successHandler(customOAuth2SuccessHandler)
//                oauth2.defaultSuccessUrl("/home")
            }
            .authorizeHttpRequests {

                it.requestMatchers(
                    "/login",
                    "/home",
                    "/cache/**",
                ).permitAll()
                it.anyRequest().authenticated()

            }

        return http.build()

    }
}