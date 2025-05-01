package com.alihan.hastatakipuygulamas.data.model

import java.time.LocalDate



data class Hasta(
    private val id: Long,
    val ad: String,
    val soyad: String,
    val tcKimlikNo: String,
    val dogumTarihi: LocalDate,
    val cinsiyet: String,
    val telefon: String,
    val email: String,
    val adres: String,
    val acilDurumKisi: String,
)

