package com.alihan.hastatakipuygulamas.data.repository

import com.alihan.hastatakipuygulamas.data.model.Hasta
import com.alihan.hastatakipuygulamas.data.remote.HastaApi
import com.alihan.hastatakipuygulamas.domain.repository.HastaRepository
import javax.inject.Inject

class HastaRepositoryImpl @Inject constructor( private val hastaApi: HastaApi) : HastaRepository {
    override suspend fun getAllPatients(): List<Hasta> {
        return hastaApi.getAllPatients()
    }

    override suspend fun addPatient(hasta: Hasta): Hasta {
        return hastaApi.addPatient(hasta)
    }

    override suspend fun updatePatient(id: Long, Hasta: Hasta): Hasta {
        return hastaApi.updatePatient(id,Hasta)
    }

    override suspend fun getPatientById(id: Long): Hasta {
        return hastaApi.getPatientById(id)
    }

    override suspend fun deletePatient(id: Long) {
        return hastaApi.deletePatient(id)
    }

}