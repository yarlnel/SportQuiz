package servolne.cima.hand

import servolne.cima.source.QuizSource

fun main() {
    QuizSource.getQuizzes(1).forEach(::println)
}