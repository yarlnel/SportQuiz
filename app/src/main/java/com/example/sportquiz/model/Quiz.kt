package com.example.sportquiz.model

data class Quiz(
    val title: String,
    val a: String,
    val b: String,
    val c: String,
    val d: String,
    val answer: Answer
) {
    enum class Answer {
        A, B, C, D
    }
}
