package com.alihan.hastatakipuygulamas.data.model

data class Doktor(
    private val id:Long,
    private val ad: String? = null,
    private val soyad: String? = null,
    private val branş: String? = null,
    private val telefon: String? = null,
    private val email: String? = null
)

