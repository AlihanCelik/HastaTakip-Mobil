package com.alihan.hastatakipuygulamas.presentation.doktorInfo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.DeleteDoktorUseCase
import com.alihan.hastatakipuygulamas.presentation.patientBilgi.PatientDeleteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoktorInfoViewModel @Inject constructor(private val deleteDoktorUseCase: DeleteDoktorUseCase): ViewModel(){
    private val _state = MutableLiveData<DoktorDeleteState>()
    val state: LiveData<DoktorDeleteState> = _state

    fun deleteDoktor(id:Long) {
        _state.value = DoktorDeleteState.Loading
        viewModelScope.launch {
            try {
                Log.d("DoctorInfoVM", "Silme denendi")
                deleteDoktorUseCase(id)
                Log.d("DoctorInfoVM", "Silme başarılı")
                _state.value = DoktorDeleteState.Success(true)
            } catch (e: Exception) {
                Log.e("DoctorInfoVM", "Silme hatası: ${e.message}")
                _state.value = DoktorDeleteState.Error("Hasta Silinemedi")
            }
        }
    }

}