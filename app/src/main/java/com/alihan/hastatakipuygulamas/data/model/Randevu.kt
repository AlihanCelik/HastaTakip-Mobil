package com.alihan.hastatakipuygulamas.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Randevu(
    private val id:Long?=null,
    val hasta:Hasta,
    val doktor:Doktor,
    val randevuTarihi:LocalDateTime,
    val durum :String


):Parcelable