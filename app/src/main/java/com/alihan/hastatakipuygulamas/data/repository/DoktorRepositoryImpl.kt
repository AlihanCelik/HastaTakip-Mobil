package com.alihan.hastatakipuygulamas.data.repository

import com.alihan.hastatakipuygulamas.data.model.Doktor
import com.alihan.hastatakipuygulamas.data.remote.DoktorApi
import com.alihan.hastatakipuygulamas.domain.repository.DoktorRepository
import javax.inject.Inject

class DoktorRepositoryImpl @Inject constructor( private val doktorApi: DoktorApi) : DoktorRepository{

    override suspend fun getAllDoktorlar(): List<Doktor> {
        return doktorApi.getAllDoktorlar()
    }

    override suspend fun getDoktorById(id: Long): Doktor {
        return doktorApi.getDoktorById(id)
    }

    override suspend fun getDoktorByBrans(brans: String): List<Doktor> {
        return doktorApi.getDoktorByBrans(brans)
    }

    override suspend fun addDoktor(doktor: Doktor): Doktor {
        return doktorApi.addDoktor(doktor)
    }

    override suspend fun updateDoktor(id: Long, doktor: Doktor): Doktor {
        return doktorApi.updateDoktor(id,doktor)
    }

    override suspend fun deleteDoktor(id: Long) {
        return doktorApi.deleteDoktor(id)
    }


}