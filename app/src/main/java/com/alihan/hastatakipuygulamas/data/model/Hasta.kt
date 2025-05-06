package com.alihan.hastatakipuygulamas.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Hasta(
    private val id: Long,
    val ad: String,
    val soyad: String,
    val tcKimlikNo: String,
    val dogumTarihi: String="a",
    val cinsiyet: String,
    val telefon: String,
    val email: String,
    val adres: String,
    val acilDurumKisi: String,
):Parcelable
