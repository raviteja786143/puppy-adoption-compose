package com.example.androiddevchallenge.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.ui.data.PetRepo
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PetDetailViewModel(petID: Int) : ViewModel() {

	private val _petDetail = MutableStateFlow(PetRepo.getPet(petID))
	val petDetail = _petDetail.asStateFlow()

	init {
	    viewModelScope.actor<StateMachine> {

		}
	}

}

sealed class StateMachine{

}
