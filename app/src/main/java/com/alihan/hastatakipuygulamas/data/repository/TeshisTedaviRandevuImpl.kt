package com.alihan.hastatakipuygulamas.data.repository

import com.alihan.hastatakipuygulamas.data.model.TeshisTedavi
import com.alihan.hastatakipuygulamas.data.remote.TeshisTedaviApi
import com.alihan.hastatakipuygulamas.domain.repository.TeshisTedaviRepository
import javax.inject.Inject

class TeshisTedaviRandevuImpl @Inject constructor(private val teshisTedaviApi: TeshisTedaviApi) :TeshisTedaviRepository{
    override suspend fun addTeshisTedavi(teshisTedavi: TeshisTedavi): TeshisTedavi {
        return teshisTedaviApi.addTeshisTedavi(teshisTedavi)
    }

    override suspend fun getTeshisTedaviByHastaId(hastaId: Long): List<TeshisTedavi> {
        return teshisTedaviApi.getTeshisTedaviByHastaId(hastaId)
    }

}