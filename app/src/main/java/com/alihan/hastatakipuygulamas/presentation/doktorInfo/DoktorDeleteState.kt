package com.alihan.hastatakipuygulamas.presentation.doktorInfo

import com.alihan.hastatakipuygulamas.presentation.patientBilgi.PatientDeleteState

sealed class DoktorDeleteState {
    object Loading : DoktorDeleteState()
    data class Success(val doktor: Boolean) : DoktorDeleteState()
    data class Error(val message: String) : DoktorDeleteState()
}