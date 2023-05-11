package com.example.sportquiz.presentation.utils

import android.widget.Button
import androidx.annotation.DrawableRes
import com.example.sportquiz.R

infix fun Button.onclick(voidLambda: () -> Unit) {
    setOnClickListener { voidLambda.invoke() }
}

@DrawableRes
fun getPageImageRes(page: Int) : Int = when(page) {
    1 -> R.drawable.img_for_quiz_page_1
    2 -> R.drawable.img_for_quiz_page_2
    3 -> R.drawable.img_for_quiz_page_3
    4 -> R.drawable.img_for_quiz_page_4
    5 -> R.drawable.img_for_quiz_page_5
    6 -> R.drawable.img_for_quiz_page_6
    7 -> R.drawable.img_for_quiz_page_7
    8 -> R.drawable.img_for_quiz_page_8
    9 -> R.drawable.img_for_quiz_page_9
    10 -> R.drawable.img_for_quiz_page_10
    11 -> R.drawable.img_for_quiz_page_11
    else -> throw Exception("Cannot define page image res cause: page out of quiz pages bounds")
}
