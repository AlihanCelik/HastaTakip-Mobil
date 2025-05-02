package com.alihan.hastatakipuygulamas.domain.usecase.Randevu

import com.alihan.hastatakipuygulamas.domain.repository.RandevuRepository
import javax.inject.Inject

class GetAllRandevuUseCase @Inject constructor(private var randevuRepository: RandevuRepository){
    suspend operator fun invoke()= randevuRepository.getAllRandevu()
}