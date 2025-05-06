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
    private val _patients = MutableLiveData<List<Hasta>>(emptyList())
    val patients: LiveData<List<Hasta>> = _patients

    fun fetchPatients() {
        viewModelScope.launch {
            try {
                val result = getAllPatientsUseCase()
                _patients.value = result
                println(_patients.value)
            } catch (e: Exception) {
                Log.e("PatientListVM", "Fetch failed", e)
                _patients.value = emptyList()
            }
        }
        println(_patients.value)
    }

}