/**
 * Copyright 2021 Remi Guillemette
 * SPDX-License-Identifier: Apache-2.0
 */
package ca.n4dev.survey.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "question")
data class Question (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val question: String,

    @ManyToOne
    @JoinColumn(name = "question_type_id")
    val questionType: QuestionType,

    @ManyToOne
    @JoinColumn(name="survey_id", nullable=false)
    val survey: Survey,

    val version: Int = 0

)