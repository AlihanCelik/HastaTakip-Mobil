package com.alihan.hastatakipuygulamas.presentation.addPatient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alihan.hastatakipuygulamas.data.model.Hasta
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.AddPatientUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.UpdatePatientUseCase
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPatientViewModel @Inject constructor(
    private val getAddPatientUseCase: AddPatientUseCase,
    private val getUpdatePatientUseCase: UpdatePatientUseCase
) : ViewModel() {

    private val _state = MutableLiveData<PatientListState>()
    val state: LiveData<PatientListState> = _state

    fun addPatient(hasta: Hasta) {
        _state.value = PatientListState.Loading
        viewModelScope.launch {
            try {
                getAddPatientUseCase(hasta)
                _state.value = PatientListState.Success(listOf(hasta))
            } catch (e: Exception) {
                _state.value = PatientListState.Error("Hasta eklenirken hata oluştu.")
            }
        }
    }

    fun updatePatient(id:Long,hasta: Hasta) {
        viewModelScope.launch {
            _state.value = PatientListState.Loading
            try {
                getUpdatePatientUseCase(id,hasta)
                _state.value = PatientListState.Success(listOf(hasta))
            } catch (e: Exception) {
                _state.value = PatientListState.Error(e.localizedMessage ?: "Hata")
            }
        }
    }

}