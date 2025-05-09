package com.alihan.hastatakipuygulamas.presentation.doktorList

import com.alihan.hastatakipuygulamas.data.model.Doktor
import com.alihan.hastatakipuygulamas.data.model.Hasta

sealed class DoktorListState {
    object Loading : DoktorListState()
    data class Success(val patients: List<Doktor>) : DoktorListState()
    data class Error(val message: String) : DoktorListState()
}