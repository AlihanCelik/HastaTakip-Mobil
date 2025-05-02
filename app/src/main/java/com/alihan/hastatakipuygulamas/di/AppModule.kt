package com.alihan.hastatakipuygulamas.di

import com.alihan.hastatakipuygulamas.data.remote.HastaApi
import com.alihan.hastatakipuygulamas.data.repository.HastaRepositoryImpl
import com.alihan.hastatakipuygulamas.domain.repository.HastaRepository
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.AddPatientUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.DeletePatientUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.GetAllPatientsUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.GetPatientByIdUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.UpdatePatientUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.HastaUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    // Retrofit Sağlayıcısı
    @Provides
    @Singleton
    fun provideHastaApi(): HastaApi {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api/hasta")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HastaApi::class.java)
    }

    // Repository Sağlayıcısı
    @Provides
    @Singleton
    fun provideHastaRepository(api: HastaApi): HastaRepository {
        return HastaRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideHastaUseCases(repository: HastaRepository): HastaUseCase {
        return HastaUseCase(
            getAllPatients = GetAllPatientsUseCase(repository),
            getPatientById = GetPatientByIdUseCase(repository),
            addPatient = AddPatientUseCase(repository),
            updatePatient = UpdatePatientUseCase(repository),
            deletePatient = DeletePatientUseCase(repository)
        )
    }
}