package com.example.bigo.myPage

data class myNotice(
    var notice: String = "",
    var description: String = "",
    var today: String = "",
    var isExpanded: Boolean = false
)

data class Check(
    val id : String? = null,
    val name : String? = null
)