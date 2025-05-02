package com.alihan.hastatakipuygulamas.data.remote

import com.alihan.hastatakipuygulamas.data.model.Randevu
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RandevuApi {
    @GET("/tumRandevular")
    suspend fun getAllRandevu(): List<Randevu>

    @GET("/hasta/{hastaId}")
    suspend fun getRandevuByHastaId(@Path("hastaId") hastaId: Long): List<Randevu>

    @POST("/ekle")
    suspend fun addRandevu(@Body randevu: Randevu): Randevu

    @PUT("/guncelle/{id}")
    suspend fun updateRandevu(@Path("id") id: Long, @Body randevu: Randevu): Randevu

    @DELETE("/sil/{id}")
    suspend fun deleteRandevu(@Path("id") id: Long)
}