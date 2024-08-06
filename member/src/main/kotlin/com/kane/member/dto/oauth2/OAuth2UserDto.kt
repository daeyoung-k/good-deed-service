package com.kane.member.dto.oauth2

data class OAuth2UserDto(
    val provider: String,
    val providerId: String,
    val email: String,
    val name: String
)
