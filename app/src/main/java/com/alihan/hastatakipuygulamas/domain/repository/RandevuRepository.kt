package com.alihan.hastatakipuygulamas.domain.repository

import com.alihan.hastatakipuygulamas.data.model.Randevu

interface RandevuRepository  {
    suspend fun getAllRandevu(): List<Randevu>
    suspend fun getRandevuByHastaId(hastaId: String): List<Randevu>
    suspend fun addRandevu(randevu: Randevu): Randevu
    suspend fun updateRandevu(id: Long, randevu: Randevu): Randevu
    suspend fun deleteRandevu(id: Long)
}