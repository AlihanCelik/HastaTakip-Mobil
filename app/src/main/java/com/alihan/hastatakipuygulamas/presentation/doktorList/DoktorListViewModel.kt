package com.alihan.hastatakipuygulamas.presentation.doktorList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alihan.hastatakipuygulamas.data.model.Doktor
import com.alihan.hastatakipuygulamas.data.model.Hasta
import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.GetAllDoktorUseCase
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoktorListViewModel @Inject constructor(private val getDoktorUseCase: GetAllDoktorUseCase) : ViewModel(){
    private val _state = MutableLiveData<DoktorListState>()
    val state: LiveData<DoktorListState> = _state

    fun fetchDoctor() {
        _state.value = DoktorListState.Loading

        viewModelScope.launch {
            try {
                val result = getDoktorUseCase()
                _state.value = DoktorListState.Success(result)
            } catch (e: Exception) {
                _state.value = DoktorListState.Error("Veriler alınırken hata oluştu")
            }
        }
    }
    fun filterDoctors(query: String): List<Doktor> {
        val currentState = _state.value
        return if (currentState is DoktorListState.Success) {
            currentState.patients.filter { doktor ->
                doktor.ad!!.contains(query, ignoreCase = true) ||
                        doktor.soyad!!.contains(query, ignoreCase = true) ||
                        doktor.branş!!.contains(query, ignoreCase = true)
            }
        } else {
            emptyList()
        }
    }

}