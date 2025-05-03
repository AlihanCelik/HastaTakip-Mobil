package com.alihan.hastatakipuygulamas.domain.usecase.TeshisTedavi

import com.alihan.hastatakipuygulamas.data.model.TeshisTedavi
import com.alihan.hastatakipuygulamas.domain.repository.TeshisTedaviRepository
import javax.inject.Inject

class AddTeshisTedaviUseCase @Inject constructor( private val repository: TeshisTedaviRepository) {
    suspend operator fun invoke(teshisTedavi: TeshisTedavi) = repository.addTeshisTedavi(teshisTedavi)
}