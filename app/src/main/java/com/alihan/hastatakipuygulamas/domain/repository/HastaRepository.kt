package com.alihan.hastatakipuygulamas.domain.repository

import com.alihan.hastatakipuygulamas.data.model.Hasta

interface HastaRepository {
    suspend fun getAllPatients(): List<Hasta>
    suspend fun addPatient(hasta:Hasta):Hasta
    suspend fun updatePatient(id:Long,Hasta:Hasta):Hasta
    suspend fun getPatientById(id:Long):Hasta
    suspend fun deletePatient(id:Long)

}