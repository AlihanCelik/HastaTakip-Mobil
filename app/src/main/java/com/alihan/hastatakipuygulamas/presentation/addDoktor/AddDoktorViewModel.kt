package com.alihan.hastatakipuygulamas.presentation.addDoktor

import androidx.lifecycle.ViewModel
import com.alihan.hastatakipuygulamas.domain.usecase.Doktor.AddDoktorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddDoktorViewModel @Inject constructor(private val addDoktorUseCase: AddDoktorUseCase) : ViewModel(){

}