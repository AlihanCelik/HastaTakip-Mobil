package com.alihan.hastatakipuygulamas.presentation.randevuList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alihan.hastatakipuygulamas.domain.usecase.Randevu.GetRandevuByHastaIdUseCase
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListState
import com.alihan.hastatakipuygulamas.presentation.randevu.RandevuState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandevuListViewModel @Inject constructor(private val getRandevuByHastaIdUseCase: GetRandevuByHastaIdUseCase) :ViewModel(){
    private val _state = MutableLiveData<RandevuState>()
    val state: LiveData<RandevuState> = _state

    fun fetchRandevu(hastaTc:String) {
        _state.value = RandevuState.Loading

        viewModelScope.launch {
            try {
                val result = getRandevuByHastaIdUseCase(hastaTc)
                _state.value = RandevuState.Success(result)
            } catch (e: Exception) {
                _state.value = RandevuState.Error("Veriler alınırken hata oluştu")
            }
        }
    }

}