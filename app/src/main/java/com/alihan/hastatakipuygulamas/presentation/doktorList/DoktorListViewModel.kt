package com.alihan.hastatakipuygulamas.presentation.doktorList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.GetAllDoktorUseCase
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

}