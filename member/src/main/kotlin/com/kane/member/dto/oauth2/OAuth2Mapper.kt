package com.kane.member.dto.oauth2

interface OAuth2Mapper {

    fun getProvider(): String

    fun getProviderId(): String

    fun getEmail(): String

    fun getName(): String
}