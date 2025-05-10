package com.alihan.hastatakipuygulamas.domain.repository

import com.alihan.hastatakipuygulamas.data.model.Doktor
import okhttp3.ResponseBody
import retrofit2.Response

interface DoktorRepository {

    suspend fun getAllDoktorlar(): List<Doktor>
    suspend fun getDoktorById(id: Long): Doktor
    suspend fun getDoktorByBrans(brans: String): List<Doktor>
    suspend fun addDoktor(doktor: Doktor): Doktor
    suspend fun updateDoktor(id: Long, doktor: Doktor): Doktor
    suspend fun deleteDoktor(id: Long): Response<ResponseBody>
}