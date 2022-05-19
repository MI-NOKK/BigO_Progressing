package com.example.bigo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class lcParcel(var lcName: String, var lcId: String) : Parcelable {
}

@Parcelize
data class lcidParcel(var dateId: String, var lcId: String): Parcelable {
}

@Parcelize
data class lcAnswerParcel(var dateId: String, var lcId: String, var answer: List<String>): Parcelable {
}

@Parcelize
data class testParcel(var answer: Array<String>): Parcelable {
}

