package com.alihan.hastatakipuygulamas.domain.usecase

import com.alihan.hastatakipuygulamas.domain.usecase.Randevu.AddRandevuUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Randevu.DeleteRandevuUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Randevu.GetAllRandevuUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Randevu.GetRandevuByHastaIdUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Randevu.UpdateRandevuUseCase

data class RandevuUseCase (
    val getAllRandevu: GetAllRandevuUseCase,
    val getRandevuByHastaId: GetRandevuByHastaIdUseCase,
    val addRandevu: AddRandevuUseCase,
    val updateRandevu: UpdateRandevuUseCase,
    val deleteRandevu: DeleteRandevuUseCase

)