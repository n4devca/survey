/**
 * Copyright 2021 Remi Guillemette
 * SPDX-License-Identifier: Apache-2.0
 */
package ca.n4dev.survey.controller

import ca.n4dev.survey.service.SurveyService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/surveys")
class SurveyController(private val surveyService: SurveyService) {

    @PostMapping("")
    fun createSurvey(@RequestParam name: String) = surveyService.createSurvey(name)

    @GetMapping("/{id}")
    fun getSurvey(@PathVariable id: Long) = surveyService.getSurvey(id)

    @PostMapping("/{id}/questions")
    fun createQuestion() {

    }
}