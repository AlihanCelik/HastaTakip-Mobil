package com.alihan.hastatakipuygulamas.domain.usecase.Randevu

import com.alihan.hastatakipuygulamas.domain.repository.RandevuRepository
import javax.inject.Inject

class DeleteRandevuUseCase @Inject constructor(private var randevuRepository: RandevuRepository) {
    suspend operator fun invoke(id: Long) = randevuRepository.deleteRandevu(id)
}