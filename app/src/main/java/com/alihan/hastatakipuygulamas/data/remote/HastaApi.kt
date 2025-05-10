package com.alihan.hastatakipuygulamas.data.remote

import com.alihan.hastatakipuygulamas.data.model.Hasta
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface HastaApi {

    @GET("api/hasta/tumHastalar")
    suspend fun getAllPatients(): List<Hasta>

    @GET("api/hasta/{id}")
    suspend fun getPatientById(@Path("id") id: Long): Hasta

    @POST("api/hasta/ekle")
    suspend fun addPatient(@Body hasta: Hasta): Hasta

    @PUT("api/hasta/guncelle/{id}")
    suspend fun updatePatient(@Path("id") id: Long, @Body hasta: Hasta): Hasta

    @DELETE("api/hasta/sil/{id}")
    suspend fun deletePatient(@Path("id") id: Long): Response<ResponseBody>
}