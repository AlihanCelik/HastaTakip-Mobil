package com.alihan.hastatakipuygulamas.presentation.patientList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alihan.hastatakipuygulamas.data.model.Hasta
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.GetAllPatientsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientListViewModel @Inject
    constructor( private val getAllPatientsUseCase: GetAllPatientsUseCase) : ViewModel() {
    private val _state = MutableLiveData<PatientListState>()
    val state: LiveData<PatientListState> = _state
    private var allPatients: List<Hasta> = emptyList()

    fun fetchPatients() {
        _state.value = PatientListState.Loading

        viewModelScope.launch {
            try {
                val result = getAllPatientsUseCase()
                _state.value = PatientListState.Success(result)
            } catch (e: Exception) {
                _state.value = PatientListState.Error("Veriler alınırken hata oluştu")
            }
        }
    }
    fun filterPatients(query: String): List<Hasta> {
        val currentState = _state.value
        return if (currentState is PatientListState.Success) {
            currentState.patients.filter { hasta ->
                hasta.ad.contains(query, ignoreCase = true) ||
                        hasta.soyad.contains(query, ignoreCase = true) ||
                        hasta.tcKimlikNo.contains(query, ignoreCase = true)
            }
        } else {
            emptyList()
        }
    }



}