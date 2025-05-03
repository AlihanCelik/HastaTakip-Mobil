package com.alihan.hastatakipuygulamas.domain.usecase

import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.AddDoktorUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.DeleteDoktorUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.GetAllDoktorUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.GetDoktorByBransUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.GetDoktorByIdUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.UpdateDoktorUseCase

data class DoktorUseCase (
    val getAllDoktor: GetAllDoktorUseCase,
    val getDoktorById: GetDoktorByIdUseCase,
    val getDoktorByBrans: GetDoktorByBransUseCase,
    val addDoktor: AddDoktorUseCase,
    val updateDoktor: UpdateDoktorUseCase,
    val deleteDoktor: DeleteDoktorUseCase

)