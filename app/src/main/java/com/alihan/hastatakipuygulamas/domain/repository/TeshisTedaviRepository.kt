package com.alihan.hastatakipuygulamas.domain.repository

import com.alihan.hastatakipuygulamas.data.model.TeshisTedavi

interface TeshisTedaviRepository {
    suspend fun addTeshisTedavi(teshisTedavi: TeshisTedavi):TeshisTedavi
    suspend fun getTeshisTedaviByHastaId(hastaId: Long): List<TeshisTedavi>
}