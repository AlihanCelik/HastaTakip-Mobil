package com.alihan.hastatakipuygulamas.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Hasta(
    val id: Long? = null,
    val ad: String,
    val soyad: String,
    val tcKimlikNo: String,
    val dogumTarihi: String="1990-05-05",
    val cinsiyet: String? = null,
    val telefon: String? = null,
    val email: String? = null,
    val adres: String? = null,
    val acilDurumKisi: String? = null,
):Parcelable
