package com.alihan.hastatakipuygulamas.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Doktor(
    val id: Long? = null,
    val ad: String? = null,
    val soyad: String? = null,
    val branş: String? = null,
    val telefon: String? = null,
    val email: String? = null
):Parcelable

