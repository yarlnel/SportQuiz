package servolne.cima.source

import servolne.cima.model.Quiz
import org.jsoup.Jsoup

object QuizSource {
    fun getQuizzes(page: Int): List<Quiz> {
        if (page < 0 || page > 11) throw Exception(" Page out of quiz pages bounds")

        val quizPageUrl = getQuizPageUrl(page)

        val sourceBody = Jsoup
            .connect(quizPageUrl)
            .userAgent("Chrome/4.0.249.0 Safari/532.5")
            .get()
            .body()

        val quizzes = mutableListOf<Quiz>()
        sourceBody.select("div.mcq").forEach { element ->
            val title = element
                .select("div.question-content, .clearfix")
                .first()
                ?.text() ?: return@forEach

            val options = element.select("div.options > div.row > div.col-md-12, .option")
            val (a, b, c, d) = options.map { option ->
                option.text()
            }

            val answerText = element
                .select("div.collapse > div.card, .card-block > blockquote")
                .text()

            val answer = when {
                "Answer: Option [A]" in answerText -> Quiz.Answer.A
                "Answer: Option [B]" in answerText -> Quiz.Answer.B
                "Answer: Option [C]" in answerText -> Quiz.Answer.C
                "Answer: Option [D]" in answerText -> Quiz.Answer.D
                else -> throw Exception(
                    """
                    Parsing error, answer is not wrapped to expected template 
                """.trimIndent()
                )
            }

            quizzes += Quiz(
                title = title,
                a = a,
                b = b,
                c = c,
                d = d,
                answer = answer
            )
        }

        return quizzes
    }

    private fun getQuizPageUrl(page: Int) =
        Constants.QuizSourceUrl + if (page > 1) "-$page" else ""

    object Constants {
        const val QuizSourceUrl =
            "https://www.gkseries.com/sports/gk-multiple-choice-questions-and-answers"
    }
}