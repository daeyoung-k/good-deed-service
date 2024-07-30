package com.kane.member.dto

import org.springframework.http.HttpStatus

data class BaseResponse(
    val code: Int? = HttpStatus.OK.value(),
    val message: String? = "Success",
    val data: Any? = emptyMap<String, String>()
)
