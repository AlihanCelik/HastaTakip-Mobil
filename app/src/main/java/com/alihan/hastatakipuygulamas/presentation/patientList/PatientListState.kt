package com.alihan.hastatakipuygulamas.presentation.patientList

import com.alihan.hastatakipuygulamas.data.model.Hasta

sealed class PatientListState {
    object Loading : PatientListState()
    data class Success(val patients: List<Hasta>) : PatientListState()
    data class Error(val message: String) : PatientListState()
}