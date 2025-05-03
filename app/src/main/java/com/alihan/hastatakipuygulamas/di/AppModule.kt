package com.alihan.hastatakipuygulamas.di

import com.alihan.hastatakipuygulamas.data.remote.DoktorApi
import com.alihan.hastatakipuygulamas.data.remote.HastaApi
import com.alihan.hastatakipuygulamas.data.remote.RandevuApi
import com.alihan.hastatakipuygulamas.data.remote.TeshisTedaviApi
import com.alihan.hastatakipuygulamas.data.repository.DoktorRepositoryImpl
import com.alihan.hastatakipuygulamas.data.repository.HastaRepositoryImpl
import com.alihan.hastatakipuygulamas.data.repository.RandevuRepositoryImpl
import com.alihan.hastatakipuygulamas.data.repository.TeshisTedaviRandevuImpl
import com.alihan.hastatakipuygulamas.domain.repository.DoktorRepository
import com.alihan.hastatakipuygulamas.domain.repository.HastaRepository
import com.alihan.hastatakipuygulamas.domain.repository.RandevuRepository
import com.alihan.hastatakipuygulamas.domain.repository.TeshisTedaviRepository
import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.AddDoktorUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.DeleteDoktorUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.GetAllDoktorUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.GetDoktorByBransUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.GetDoktorByIdUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.UpdateDoktorUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.DoktorUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.AddPatientUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.DeletePatientUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.GetAllPatientsUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.GetPatientByIdUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.UpdatePatientUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.HastaUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Randevu.AddRandevuUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Randevu.DeleteRandevuUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Randevu.GetAllRandevuUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Randevu.GetRandevuByHastaIdUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Randevu.UpdateRandevuUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.RandevuUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.TeshisTedavi.AddTeshisTedaviUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.TeshisTedavi.GetTeshisTedaviByHastaIdUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.TeshisTedaviUseCase
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

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideHastaApi(retrofit: Retrofit): HastaApi {
        return retrofit.create(HastaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRandevuApi(retrofit: Retrofit): RandevuApi {
        return retrofit.create(RandevuApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDoktorApi(retrofit: Retrofit):DoktorApi{
        return retrofit.create(DoktorApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTeshisTedaviApi(retrofit: Retrofit): TeshisTedaviApi {
        return retrofit.create(TeshisTedaviApi::class.java)
    }

    // Repository Sağlayıcısı
    @Provides
    @Singleton
    fun provideRandevuRepository(api: RandevuApi): RandevuRepository {
        return RandevuRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideHastaRepository(api: HastaApi): HastaRepository {
        return HastaRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideDoktorRepository(api:DoktorApi):DoktorRepository{
        return DoktorRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideTeshisTedaviRepository(api: TeshisTedaviApi): TeshisTedaviRepository {
        return TeshisTedaviRandevuImpl(api)
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

    @Provides
    @Singleton
    fun provideRandevuUseCases(repository: RandevuRepository): RandevuUseCase {
        return RandevuUseCase(
            getAllRandevu = GetAllRandevuUseCase(repository),
            getRandevuByHastaId = GetRandevuByHastaIdUseCase(repository),
            addRandevu = AddRandevuUseCase(repository),
            updateRandevu = UpdateRandevuUseCase(repository),
            deleteRandevu = DeleteRandevuUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideDoktorUseCases(repository: DoktorRepository): DoktorUseCase {
        return DoktorUseCase(
            getAllDoktor = GetAllDoktorUseCase(repository),
            getDoktorById = GetDoktorByIdUseCase(repository),
            getDoktorByBrans = GetDoktorByBransUseCase(repository),
            addDoktor = AddDoktorUseCase(repository),
            updateDoktor = UpdateDoktorUseCase(repository),
            deleteDoktor = DeleteDoktorUseCase(repository)
        )

    }

    @Provides
    @Singleton
    fun provideTeshisTedaviUseCases(repository: TeshisTedaviRepository): TeshisTedaviUseCase {
        return TeshisTedaviUseCase(
            getTeshisTedaviByHastaIdUseCase = GetTeshisTedaviByHastaIdUseCase(repository),
            addTeshisTedaviUseCase  = AddTeshisTedaviUseCase(repository),

        )
    }
}