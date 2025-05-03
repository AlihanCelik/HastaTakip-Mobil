package com.alihan.hastatakipuygulamas.domain.usecase

import com.alihan.hastatakipuygulamas.domain.usecase.TeshisTedavi.AddTeshisTedaviUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.TeshisTedavi.GetTeshisTedaviByHastaIdUseCase

data class TeshisTedaviUseCase (
    val getTeshisTedaviByHastaIdUseCase: GetTeshisTedaviByHastaIdUseCase,
    val addTeshisTedaviUseCase: AddTeshisTedaviUseCase,
)