package com.alihan.hastatakipuygulamas.domain.usecase.Hasta

import com.alihan.hastatakipuygulamas.domain.repository.HastaRepository
import javax.inject.Inject


class DeletePatientUseCase @Inject constructor(
    private val repository: HastaRepository
) {
    suspend operator fun invoke(id: Long) = repository.deletePatient(id)
}