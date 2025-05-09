package com.alihan.hastatakipuygulamas.presentation.patientBilgi

import androidx.lifecycle.ViewModel
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.DeletePatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PatientInfoViewModel @Inject constructor(private val deletePatientUseCase: DeletePatientUseCase,)
    :ViewModel(){

}