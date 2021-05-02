/**
 * Copyright 2021 Remi Guillemette
 * SPDX-License-Identifier: Apache-2.0
 */
package ca.n4dev.survey.service

import ca.n4dev.survey.model.Survey
import ca.n4dev.survey.repository.SurveyRepository
import ca.n4dev.survey.resource.SurveyResource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface SurveyService {
    fun createSurvey(name: String): SurveyResource

    fun getSurvey(id: Long): SurveyResource?
}

@Service
class SurveyServiceImpl(private val surveyRepository: SurveyRepository) : SurveyService {

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    override fun createSurvey(name: String) : SurveyResource {
        val survey = Survey(name = name, question = listOf())
        return surveyRepository.save(survey).let {
            SurveyResource(it.id, it.name)
        }
    }


    @PreAuthorize("hasRole('USER')")
    @Transactional
    override fun getSurvey(id: Long): SurveyResource? {
        return surveyRepository.findById(id).map {
            SurveyResource(it.id, it.name)
        }.orElse(null)
    }
}