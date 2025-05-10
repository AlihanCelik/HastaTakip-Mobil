package com.alihan.hastatakipuygulamas.presentation.randevu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.GetAllDoktorUseCase
import com.alihan.hastatakipuygulamas.domain.usecase.Randevu.AddRandevuUseCase
import com.alihan.hastatakipuygulamas.presentation.doktorList.DoktorListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandevuViewModel @Inject constructor(
    private val addRandevuUseCase: AddRandevuUseCase,
    private val getAllDoktorUseCase: GetAllDoktorUseCase):ViewModel() {
    private val _state = MutableLiveData<DoktorListState>()
    val state: LiveData<DoktorListState> = _state

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

}