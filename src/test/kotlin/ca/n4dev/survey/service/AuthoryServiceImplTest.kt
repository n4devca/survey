package ca.n4dev.survey.service

import ca.n4dev.survey.model.Authority
import ca.n4dev.survey.repository.AuthorityRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

/**
 * Copyright 2021 Remi Guillemette
 * SPDX-License-Identifier: Apache-2.0
 */
@SpringBootTest
internal class AuthoryServiceImplTest {

    @MockBean
    private lateinit var authorityRepository: AuthorityRepository

    @Autowired
    private lateinit var authorityService: AuthorityService

    @Test
    fun getAllAuthority() {

        `when`(authorityRepository.findAll()).thenReturn(this.allRoles())

        val authorities = authorityService.getAllAuthority()

        assertTrue(authorities.isNotEmpty(),"The list should not be empty")
    }


    private fun allRoles(): List<Authority> =
        listOf(Authority(code = "ROLE_USER"), Authority(code = "ROLE_ADMIN"))
}