package com.kane.member.controller

import com.kane.member.dto.BaseResponse
import org.springframework.web.bind.annotation.*

@RestController
class MemberController {

    @GetMapping("/login")
    fun memberLogin(
    ): BaseResponse {
        return BaseResponse()
    }
}