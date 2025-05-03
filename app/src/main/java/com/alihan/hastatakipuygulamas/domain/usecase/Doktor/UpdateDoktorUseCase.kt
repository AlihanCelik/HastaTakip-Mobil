package com.alihan.hastatakipuygulamas.domain.usecase.Doktor

import com.alihan.hastatakipuygulamas.data.model.Doktor
import com.alihan.hastatakipuygulamas.domain.repository.DoktorRepository
import javax.inject.Inject

class UpdateDoktorUseCase @Inject constructor(private val doktorRepository: DoktorRepository) {
    suspend operator fun invoke(id: Long, doktor: Doktor)=doktorRepository.updateDoktor(id,doktor)
}