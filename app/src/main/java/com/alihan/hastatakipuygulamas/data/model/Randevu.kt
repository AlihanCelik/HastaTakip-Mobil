package com.alihan.hastatakipuygulamas.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Randevu(
    private val id:Long?=null,
    val hasta: HastaRef,
    val doktor: DoktorRef,
    val randevuTarihi:String,
    val durum :String


):Parcelable

@Parcelize
data class HastaRef(
    val tcKimlikNo: String,
    val ad: String,
    val soyad: String
):Parcelable


@Parcelize
data class DoktorRef(
    val id: Long
):Parcelable
