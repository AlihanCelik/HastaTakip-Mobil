package com.alihan.hastatakipuygulamas.data.remote

import com.alihan.hastatakipuygulamas.data.model.TeshisTedavi
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TeshisTedaviApi  {

    @POST("api/teshisTedavi/ekle")
    suspend fun addTeshisTedavi(@Body teshisTedavi: TeshisTedavi): TeshisTedavi

    @GET("api/teshisTedavi/hasta/{hastaId}")
    suspend fun getTeshisTedaviByHastaId(@Path("hastaId") hastaId: Long): List<TeshisTedavi>


}