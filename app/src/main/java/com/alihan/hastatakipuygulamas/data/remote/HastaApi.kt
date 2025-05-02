package com.alihan.hastatakipuygulamas.data.remote

import com.alihan.hastatakipuygulamas.data.model.Hasta
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface HastaApi {

    @GET("/tumHastalar")
    suspend fun getAllPatients(): List<Hasta>

    @GET("/{id}")
    suspend fun getPatientById(@Path("id") id: Long): Hasta

    @POST("/ekle")
    suspend fun addPatient(@Body hasta: Hasta): Hasta

    @PUT("/guncelle/{id}")
    suspend fun updatePatient(@Path("id") id: Long, @Body hasta: Hasta): Hasta

    @DELETE("/sil/{id}")
    suspend fun deletePatient(@Path("id") id: Long)
}