package com.alihan.hastatakipuygulamas.data.model

data class TeshisTedavi(
    private var Id:Long,
    val hasta: Hasta? = null ,
    val teshis: String? = null,
    val tedaviYontemi: String? = null,
    val kullanilanIlaclar: String? = null,
    val tedaviNotlari: String? = null
)


