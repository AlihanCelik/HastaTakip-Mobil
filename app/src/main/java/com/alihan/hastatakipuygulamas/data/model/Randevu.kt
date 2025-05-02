package com.alihan.hastatakipuygulamas.data.model

import java.time.LocalDateTime

data class Randevu(
    private val id:Long,
    val hasta:Hasta,
    val doktor:Doktor,
    val randevuTarihi:LocalDateTime,
    val durum :String


)