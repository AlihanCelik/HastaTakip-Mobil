package com.alihan.hastatakipuygulamas.presentation.patientBilgi

import com.alihan.hastatakipuygulamas.data.model.Hasta

sealed class PatientDeleteState {
    object Loading : PatientDeleteState()
    data class Success(val patients: Boolean) : PatientDeleteState()
    data class Error(val message: String) : PatientDeleteState()
}