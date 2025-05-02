package com.alihan.hastatakipuygulamas.domain.model

import java.time.LocalDate

data class HastaDomainModel(
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
