/**
 * Copyright 2021 Remi Guillemette
 * SPDX-License-Identifier: Apache-2.0
 */
package ca.n4dev.survey.service

import ca.n4dev.survey.repository.AuthorityRepository
import ca.n4dev.survey.resource.AuthorityResource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface AuthorityService {
    fun getAllAuthority(): List<AuthorityResource>
}

@Service
class AuthorityServiceImpl(private val authorityRepository: AuthorityRepository) : AuthorityService {

    @Transactional(readOnly = true)
    override fun getAllAuthority(): List<AuthorityResource> {
        return authorityRepository.findAll().map {
            AuthorityResource(it.code)
        }
    }

}