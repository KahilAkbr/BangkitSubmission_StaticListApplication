package com.example.filmin
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val name: String,
    val photo: Int,
    val views: String,
    val rating: String,
    val duration: String,
    val producer: String,
    val synopsis: String
) : Parcelable
