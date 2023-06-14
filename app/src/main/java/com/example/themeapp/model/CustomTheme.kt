package com.example.themeapp.model

enum class CustomTheme(val value: Int) {
    A(0), B(1), C(2), D(3);

    companion object {
        fun getTheme(value: Int?): CustomTheme {
            return values().firstOrNull { x -> x.value == value } ?: A
        }
    }
}