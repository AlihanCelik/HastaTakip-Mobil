package com.alihan.hastatakipuygulamas.domain.usecase.Hasta

import com.alihan.hastatakipuygulamas.data.model.Hasta
import com.alihan.hastatakipuygulamas.domain.repository.HastaRepository
import javax.inject.Inject

class AddPatientUseCase @Inject constructor(
    private val repository: HastaRepository
) {
    suspend operator fun invoke(hasta: Hasta) = repository.addPatient(hasta)
}