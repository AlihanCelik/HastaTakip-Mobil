package com.alihan.hastatakipuygulamas.presentation.doktorList

import androidx.lifecycle.ViewModel
import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.GetAllDoktorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoktorListViewModel @Inject constructor(private val getDoktorUseCase: GetAllDoktorUseCase) : ViewModel(){


}