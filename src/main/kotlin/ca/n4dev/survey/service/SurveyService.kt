/**
 * Copyright 2021 Remi Guillemette
 * SPDX-License-Identifier: Apache-2.0
 */
package ca.n4dev.survey.service

import ca.n4dev.survey.model.Question
import ca.n4dev.survey.model.Survey
import ca.n4dev.survey.repository.QuestionRepository
import ca.n4dev.survey.repository.SurveyRepository
import ca.n4dev.survey.resource.QuestionResource
import ca.n4dev.survey.resource.SurveyResource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface SurveyService {

    fun getSurveys(): List<SurveyResource>

    fun createSurvey(name: String): SurveyResource

    fun getSurvey(id: Long): SurveyResource?

    fun createQuestion(surveyId: Long, questionResource: QuestionResource): Result<QuestionResource>

    fun createQuestions(surveyId: Long, questionResource: List<QuestionResource>): Result<List<QuestionResource>>
}

@Service
class SurveyServiceImpl(private val surveyRepository: SurveyRepository,
                        private val questionRepository: QuestionRepository) : SurveyService {


    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    override fun getSurveys(): List<SurveyResource> {
        return surveyRepository.findAll().map {
            SurveyResource(it.id, it.name)
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    override fun createSurvey(name: String): SurveyResource {
        val survey = Survey(name, listOf())
        return surveyRepository.save(survey).let {
            SurveyResource(it.id, it.name)
        }
    }


    @PreAuthorize("hasRole('USER')")
    @Transactional
    override fun getSurvey(id: Long): SurveyResource? {
        return surveyRepository.findById(id).map { survey: Survey ->
            SurveyResource(survey.id, survey.name, survey.questions.map { QuestionResource(it.id, it.question, it.questionType) })
        }.orElse(null)
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    override fun createQuestion(surveyId: Long, questionResource: QuestionResource): Result<QuestionResource> {

        val optionalSurvey = surveyRepository.findById(surveyId)

        return if (optionalSurvey.isPresent) {

            val question = Question(questionResource.question,
                                    questionResource.questionType,
                                    optionalSurvey.get())

            try {

                questionRepository.save(question).let {
                    Result.Success(QuestionResource(it.id, it.question, it.questionType))
                }

            } catch (exception: Exception) {
                Result.Error("Database error.", exception)
            }
        } else {
            Result.Error("Invalid id.")
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    override fun createQuestions(surveyId: Long, questionResource: List<QuestionResource>): Result<List<QuestionResource>> {
        val optionalSurvey = surveyRepository.findById(surveyId)

        return if (optionalSurvey.isPresent) {
            try {

                val survey = optionalSurvey.get()

                val questions = questionResource.map {
                    Question(it.question,
                             it.questionType,
                             survey)
                }

                Result.Success(
                    questionRepository.saveAll(questions).map {
                        QuestionResource(it.id, it.question, it.questionType)
                    }
                )

            } catch (exception: Exception) {
                Result.Error("Database error.", exception)
            }
        } else {
            Result.Error("Invalid id.")
        }
    }
}