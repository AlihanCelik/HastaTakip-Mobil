package com.alihan.hastatakipuygulamas.presentation.addPatient

import androidx.lifecycle.ViewModel
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.AddPatientUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.UpdatePatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddPatientViewModel @Inject constructor(
    private val getAddPatientUseCase: AddPatientUseCase,
    private val getUpdatePatientUseCase: UpdatePatientUseCase
) : ViewModel() {

}