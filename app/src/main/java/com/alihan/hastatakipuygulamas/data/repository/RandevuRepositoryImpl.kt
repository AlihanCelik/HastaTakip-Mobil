package com.alihan.hastatakipuygulamas.data.repository

import com.alihan.hastatakipuygulamas.data.model.Randevu
import com.alihan.hastatakipuygulamas.data.remote.RandevuApi
import com.alihan.hastatakipuygulamas.domain.repository.RandevuRepository
import javax.inject.Inject

class RandevuRepositoryImpl @Inject constructor(private val randevuApi: RandevuApi) : RandevuRepository {
    override suspend fun getAllRandevu(): List<Randevu> {
        return randevuApi.getAllRandevu()
    }

    override suspend fun getRandevuByHastaId(hastaId: String): List<Randevu> {
        return randevuApi.getRandevuByHastaId(hastaId)
    }

    override suspend fun addRandevu(randevu: Randevu): Randevu {
        return  randevuApi.addRandevu(randevu)
    }

    override suspend fun updateRandevu(id: Long, randevu: Randevu): Randevu {
        return randevuApi.updateRandevu(id, randevu)
    }

    override suspend fun deleteRandevu(id: Long) {
        return randevuApi.deleteRandevu(id)
    }

}
