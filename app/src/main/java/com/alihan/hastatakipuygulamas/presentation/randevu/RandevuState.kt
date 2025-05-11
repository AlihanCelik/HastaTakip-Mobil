package com.alihan.hastatakipuygulamas.presentation.randevu

import com.alihan.hastatakipuygulamas.data.model.Hasta
import com.alihan.hastatakipuygulamas.data.model.Randevu

sealed class RandevuState {
    object Loading : RandevuState()
    data class Success(val randevu: List<Randevu>) : RandevuState()
    data class Error(val message: String) : RandevuState()
}