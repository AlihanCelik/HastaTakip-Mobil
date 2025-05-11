package com.alihan.hastatakipuygulamas.presentation.randevu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alihan.hastatakipuygulamas.data.model.Hasta
import com.alihan.hastatakipuygulamas.data.model.Randevu
import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.GetAllDoktorUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Randevu.AddRandevuUseCase
import com.alihan.hastatakipuygulamas.presentation.doktorList.DoktorListState
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandevuViewModel @Inject constructor(
    private val addRandevuUseCase: AddRandevuUseCase,
    private val getAllDoktorUseCase: GetAllDoktorUseCase):ViewModel() {
    private val _state = MutableLiveData<DoktorListState>()
    val state: LiveData<DoktorListState> = _state

    private val _state2 = MutableLiveData<RandevuState>()
    val state2: LiveData<RandevuState> = _state2


    fun fetchDoctors() {
        _state.value = DoktorListState.Loading

        viewModelScope.launch {
            try {
                val result = getAllDoktorUseCase()
                _state.value = DoktorListState.Success(result)
            } catch (e: Exception) {
                _state.value = DoktorListState.Error("Veriler alınırken hata oluştu")
            }
        }
    }

    fun addRandevu(randevu: Randevu) {
        _state2.value = RandevuState.Loading
        viewModelScope.launch {
            try {
                addRandevuUseCase(randevu)
                _state2.value = RandevuState.Success(listOf(randevu))
            } catch (e: Exception) {
                Log.e("RandevuInfoVM", "olusturma hatası: ${e.message}")
                _state2.value = RandevuState.Error("Randevu eklenirken hata oluştu.($e)")
            }
        }
    }



}