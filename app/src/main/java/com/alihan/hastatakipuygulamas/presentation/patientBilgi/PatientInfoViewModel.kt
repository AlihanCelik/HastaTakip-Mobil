package com.alihan.hastatakipuygulamas.presentation.patientBilgi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alihan.hastatakipuygulamas.domain.usecase.Hasta.DeletePatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientInfoViewModel @Inject constructor(private val deletePatientUseCase: DeletePatientUseCase,)
    :ViewModel(){
    private val _state = MutableLiveData<PatientDeleteState>()
    val state: LiveData<PatientDeleteState> = _state

    fun deletePatients(id:Long) {
        _state.value = PatientDeleteState.Loading
        viewModelScope.launch {
            try {
                Log.d("PatientInfoVM", "Silme denendi")
                deletePatientUseCase(id)
                Log.d("PatientInfoVM", "Silme başarılı")
                _state.value = PatientDeleteState.Success(true)
            } catch (e: Exception) {
                Log.e("PatientInfoVM", "Silme hatası: ${e.message}")
                _state.value = PatientDeleteState.Error("Hasta Silinemedi")
            }
        }
    }


}