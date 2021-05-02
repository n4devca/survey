/**
 * Copyright 2021 Remi Guillemette
 * SPDX-License-Identifier: Apache-2.0
 */
package ca.n4dev.survey.repository

import ca.n4dev.survey.model.SurveyUser
import org.springframework.data.jpa.repository.JpaRepository

interface SurveyUserRepository : JpaRepository<SurveyUser, Long> {
    fun findByUsername(username: String): SurveyUser?
}