package com.alihan.hastatakipuygulamas.domain.usecase.Doktor

import com.alihan.hastatakipuygulamas.data.model.Doktor
import com.alihan.hastatakipuygulamas.domain.repository.DoktorRepository
import javax.inject.Inject

class AddDoktorUseCase @Inject constructor(private val repository: DoktorRepository) {
    suspend operator fun invoke(doktor: Doktor) = repository.addDoktor(doktor)

}