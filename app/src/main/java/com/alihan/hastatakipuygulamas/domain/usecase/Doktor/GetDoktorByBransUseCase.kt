package com.alihan.hastatakipuygulamas.domain.usecase.Doktor

import com.alihan.hastatakipuygulamas.domain.repository.DoktorRepository
import javax.inject.Inject

class GetDoktorByBransUseCase @Inject constructor(private val doktorRepository: DoktorRepository ){
    suspend operator fun invoke(brans: String) = doktorRepository.getDoktorByBrans(brans)
}