/**
 * Copyright 2021 Remi Guillemette
 * SPDX-License-Identifier: Apache-2.0
 */
package ca.n4dev.survey.resource

import ca.n4dev.survey.model.QuestionType

data class QuestionResource(

    val id: Long = 0,

    val question: String,

    val questionType: QuestionType,

    val response: String? = null
)
