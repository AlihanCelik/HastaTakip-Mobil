package com.alihan.hastatakipuygulamas.data.remote

import com.alihan.hastatakipuygulamas.data.model.Doktor
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DoktorApi {

    @GET("api/doktor/tumDoktorlar")
    suspend fun getAllDoktorlar(): List<Doktor>

    @GET("api/doktor/{id}")
    suspend fun getDoktorById(@Path("id") id: Long): Doktor

    @GET("api/doktor/branş/{branş}")
    suspend fun getDoktorByBrans(@Path("branş") brans: String): List<Doktor>

    @POST("api/doktor/ekle")
    suspend fun addDoktor(@Body doktor: Doktor): Doktor

    @PUT("api/doktor/guncelle/{id}")
    suspend fun updateDoktor(@Path("id") id: Long, @Body doktor: Doktor): Doktor

    @DELETE("api/doktor/sil/{id}")
    suspend fun deleteDoktor(@Path("id") id: Long): Response<ResponseBody>

}