package com.example.sportquiz.presentation.utils

import android.widget.Button

infix fun Button.onclick(voidLambda: () -> Unit) {
    setOnClickListener { voidLambda.invoke() }
}