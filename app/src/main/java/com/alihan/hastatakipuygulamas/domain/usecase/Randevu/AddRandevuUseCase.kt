package com.alihan.hastatakipuygulamas.domain.usecase.Randevu

import com.alihan.hastatakipuygulamas.data.model.Randevu
import com.alihan.hastatakipuygulamas.domain.repository.RandevuRepository
import javax.inject.Inject

class AddRandevuUseCase @Inject constructor(private var randevuRepository: RandevuRepository) {
    suspend operator fun invoke(randevu: Randevu) = randevuRepository.addRandevu(randevu)
}