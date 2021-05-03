package ca.n4dev.survey.service

import ca.n4dev.survey.BaseTest
import ca.n4dev.survey.model.Survey
import ca.n4dev.survey.repository.SurveyRepository
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean

/**
 * Copyright 2021 Remi Guillemette
 * SPDX-License-Identifier: Apache-2.0
 */
internal class SurveyServiceImplTest : BaseTest {


    @MockBean
    private lateinit var surveyRepository: SurveyRepository

    @Autowired
    private lateinit var surveyService: SurveyService

    @Test
    fun getSurveys() {
        `when`(surveyRepository.findAll()).thenReturn(mockSurveys())

        val surveys = surveyService.getSurveys()

        assertTrue(surveys.isNotEmpty(), "2 surveys should have been returned")

        // ugly, rewrite that
        assertNotNull(surveys.any { it.id == 1L })
        assertNotNull(surveys.any { it.id == 2L })
        assertNotNull(surveys.any { it.name == "test 1" })
        assertNotNull(surveys.any { it.name == "test 2" })
    }


    private fun mockSurveys() =
        listOf(
            Survey("test 1", listOf(), 1, 0),
            Survey("test 2", listOf(), 2, 0)
        )
}