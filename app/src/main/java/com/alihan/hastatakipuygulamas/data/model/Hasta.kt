package com.alihan.hastatakipuygulamas.data.model



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
)
