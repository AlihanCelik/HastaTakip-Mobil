package com.alihan.hastatakipuygulamas.domain.usecase.Doktor

import com.alihan.hastatakipuygulamas.domain.repository.DoktorRepository
import javax.inject.Inject

class DeleteDoktorUseCase @Inject constructor(private val repository: DoktorRepository)  {
    suspend operator fun invoke(id: Long) = repository.deleteDoktor(id)
}