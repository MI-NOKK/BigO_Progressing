package com.example.bigo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class coParcel(var coId: String, var coQ: String): Parcelable {
}
@Parcelize
data class coQParcel(var coId: String,  var coQ: String, var answer:String, var level: String): Parcelable {
}

@Parcelize
data class sqlParcel(var sqlId: String, var sqlQ: String): Parcelable {
}

@Parcelize
data class sqlQParcel(var sqlId: String, var sqlQ: String, var answer:String, var level: String): Parcelable {
}

@Parcelize
data class lcParcel(var lcName: String, var lcId: String) : Parcelable {
}

@Parcelize
data class lcidParcel(var dateId: String, var lcId: String): Parcelable {
}

@Parcelize
data class lcAnswerParcel(var dateId: String, var lcId: String, var answer: Array<String>): Parcelable {
}

@Parcelize
data class testParcel(var answer: Array<String>): Parcelable {
}

