package com.kane.gooddeed.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GoodDeedController {

    @GetMapping("/good-deed")
    fun getGoodDeedList(): List<String> {
        return listOf("goodDeed1", "goodDeed2", "goodDeed3")
    }
}