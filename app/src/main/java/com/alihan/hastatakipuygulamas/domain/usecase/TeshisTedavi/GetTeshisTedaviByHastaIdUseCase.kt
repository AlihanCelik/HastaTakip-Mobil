package com.alihan.hastatakipuygulamas.domain.usecase.TeshisTedavi

import com.alihan.hastatakipuygulamas.domain.repository.TeshisTedaviRepository
import javax.inject.Inject

class GetTeshisTedaviByHastaIdUseCase @Inject constructor(private val repository: TeshisTedaviRepository) {
    suspend operator fun invoke(hastaId: Long) = repository.getTeshisTedaviByHastaId(hastaId)
}