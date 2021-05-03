/**
 * Copyright 2021 Remi Guillemette
 * SPDX-License-Identifier: Apache-2.0
 */
package ca.n4dev.survey.resource

data class SurveyResource(

    val id: Long,

    val name: String,

    val questions: List<QuestionResource> = listOf()

)