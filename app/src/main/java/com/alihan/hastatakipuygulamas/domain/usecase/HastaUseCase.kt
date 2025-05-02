package com.alihan.hastatakipuygulamas.domain.usecase

import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.AddPatientUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.DeletePatientUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.GetAllPatientsUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.GetPatientByIdUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.UpdatePatientUseCase

data class HastaUseCase(
    val getAllPatients: GetAllPatientsUseCase,
    val getPatientById: GetPatientByIdUseCase,
    val addPatient: AddPatientUseCase,
    val updatePatient: UpdatePatientUseCase,
    val deletePatient: DeletePatientUseCase
)
