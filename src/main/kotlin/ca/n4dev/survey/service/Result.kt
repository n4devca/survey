/**
 * Copyright 2021 Remi Guillemette
 * SPDX-License-Identifier: Apache-2.0
 */
package ca.n4dev.survey.service

sealed class Result<out V> {
    data class Success<out V>(val value: V) : Result<V>()
    data class Error(val message: String, val exception: Exception? = null) : Result<Nothing>()
}