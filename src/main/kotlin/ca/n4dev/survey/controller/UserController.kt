/**
 * Copyright 2021 Remi Guillemette
 * SPDX-License-Identifier: Apache-2.0
 */
package ca.n4dev.survey.controller

import ca.n4dev.survey.service.AuthorityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class UserController(private val pAuthorityService: AuthorityService) {

    @GetMapping("/authorities")
    fun getAllAuthority(principal: Principal)= pAuthorityService.getAllAuthority()
}