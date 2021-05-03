/**
 * Copyright 2021 Remi Guillemette
 * SPDX-License-Identifier: Apache-2.0
 */
package ca.n4dev.survey.controller

import ca.n4dev.survey.resource.QuestionResource
import ca.n4dev.survey.service.Result
import ca.n4dev.survey.service.SurveyService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/surveys")
class SurveyController(private val surveyService: SurveyService) {

    private val logger: Logger = LoggerFactory.getLogger(SurveyController::class.java)

    @GetMapping("")
    fun getSurveys() = surveyService.getSurveys()

    @PostMapping("")
    fun createSurvey(@RequestParam name: String) = surveyService.createSurvey(name)

    @GetMapping("/{id}")
    fun getSurvey(@PathVariable id: Long) = surveyService.getSurvey(id)

    @PostMapping("/{id}/questions")
    fun createQuestions(@PathVariable id: Long, @RequestBody questions: List<QuestionResource>): ResponseEntity<List<QuestionResource>> {

        if (questions.isEmpty()) {
            return ResponseEntity.badRequest().build()
        }

        return when (val result = surveyService.createQuestions(id, questions)) {

            is Result.Success -> {
                ResponseEntity.ok(result.value)
            }

            is Result.Error -> {

                logger.error(result.toString())

                ResponseEntity(
                    if (result.exception != null) {
                        HttpStatus.INTERNAL_SERVER_ERROR
                    } else {
                        HttpStatus.BAD_REQUEST
                    }
                )
            }
        }

    }
}