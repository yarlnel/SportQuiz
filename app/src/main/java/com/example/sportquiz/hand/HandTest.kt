package com.example.sportquiz.hand

import com.example.sportquiz.source.QuizSource

fun main() {
    QuizSource.getQuizzes(1).forEach(::println)
}