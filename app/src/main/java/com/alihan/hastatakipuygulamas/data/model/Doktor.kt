package com.alihan.hastatakipuygulamas.data.model

data class Doktor(
    private val id:Long,
    val ad: String? = null,
    val soyad: String? = null,
    val branş: String? = null,
    val telefon: String? = null,
    val email: String? = null
)

