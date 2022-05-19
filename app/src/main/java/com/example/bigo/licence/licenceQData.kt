package com.example.bigo.licence


class licenceQData(
    val question: String? = null,
    val example01: String? = null,
    val example02: String? = null,
    val example03: String? = null,
    val example04: String? = null
    ) {
    fun getPackageName(): String? {
        return question
    }

    fun getPriceList(): List<String?> {
        val example = listOf(example01, example02, example03, example04)
        return example
    }
}