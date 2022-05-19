package com.example.bigo.licence


class licenceQData1(
    val question: String? = null,
    val example: MutableList<String?>
    ) {
    fun getPackageName(): String? {
        return question
    }

    fun getPriceList(): MutableList<String?> {
        return example
    }
}