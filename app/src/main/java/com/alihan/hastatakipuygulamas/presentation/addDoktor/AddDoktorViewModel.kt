package com.alihan.hastatakipuygulamas.presentation.addDoktor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alihan.hastatakipuygulamas.data.model.Doktor
import com.alihan.hastatakipuygulamas.data.model.Hasta
import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.AddDoktorUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.UpdateDoktorUseCase
import com.alihan.hastatakipuygulamas.presentation.doktorList.DoktorListState
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddDoktorViewModel @Inject constructor(private val addDoktorUseCase: AddDoktorUseCase,
    private val updateDoktorUseCase: UpdateDoktorUseCase) : ViewModel(){
    private val _state = MutableLiveData<DoktorListState>()
    val state: LiveData<DoktorListState> = _state

    fun addPatient(doktor: Doktor) {
        _state.value = DoktorListState.Loading
        viewModelScope.launch {
            try {
                addDoktorUseCase(doktor)
                _state.value = DoktorListState.Success(listOf(doktor))
            } catch (e: Exception) {
                _state.value = DoktorListState.Error("Doktor eklenirken hata oluştu.")
            }
        }
    }

    fun updatePatient(id:Long,doktor: Doktor) {
        viewModelScope.launch {
            _state.value = DoktorListState.Loading
            try {
                updateDoktorUseCase(id, doktor)
                _state.value = DoktorListState.Success(listOf(doktor))
            } catch (e: Exception) {
                _state.value = DoktorListState.Error(e.localizedMessage ?: "Hata")
            }
        }
    }

}