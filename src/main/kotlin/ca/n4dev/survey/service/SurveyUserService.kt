/**
 * Copyright 2021 Remi Guillemette
 * SPDX-License-Identifier: Apache-2.0
 */
package ca.n4dev.survey.service

import ca.n4dev.survey.model.SurveyUser
import ca.n4dev.survey.repository.SurveyUserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface SurveyUserService : UserDetailsService

@Service
class SurveyUserServiceImpl(private val surveyUserRepository: SurveyUserRepository) : SurveyUserService {

    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String?): UserDetails {

        if (username != null) {
            return surveyUserRepository.findByUsername(username)?.let {
                UserDetailsImpl(it)
            } ?: throw UsernameNotFoundException("$username was not found")
        }

        throw UsernameNotFoundException("$username was not found")
    }
}

class UserDetailsImpl(surveyUser: SurveyUser) : UserDetails {

    private val userName = surveyUser.username
    private val passwd = surveyUser.passwd
    private val authorities = surveyUser.authorities.map { SimpleGrantedAuthority(it.code) }

    override fun getAuthorities() = this.authorities

    override fun getPassword() = this.passwd

    override fun getUsername() = this.userName

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true

}