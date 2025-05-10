package com.alihan.hastatakipuygulamas.domain.repository

import com.alihan.hastatakipuygulamas.data.model.Hasta
import okhttp3.ResponseBody
import retrofit2.Response

interface HastaRepository {
    suspend fun getAllPatients(): List<Hasta>
    suspend fun addPatient(hasta:Hasta):Hasta
    suspend fun updatePatient(id:Long,Hasta:Hasta):Hasta
    suspend fun getPatientById(id:Long):Hasta
    suspend fun deletePatient(id:Long): Response<ResponseBody>

}