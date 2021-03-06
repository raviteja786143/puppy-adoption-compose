package com.example.androiddevchallenge.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.ui.data.Pet
import com.example.androiddevchallenge.ui.data.PetRepo
import com.example.androiddevchallenge.ui.theme.ThemeManager.Theme.DARK_MODE
import com.example.androiddevchallenge.ui.theme.ThemeManager.Theme.LIGHT_MODE
import com.example.androiddevchallenge.ui.theme.ThemeManager.applyTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PetListViewModel(isDark: Boolean) : ViewModel() {

	private val _petList = MutableStateFlow(emptyList<Pet>())
	val petList = _petList.asStateFlow()

	private val _isDark = MutableStateFlow(isDark)
	val isDark = _isDark.asStateFlow()

	init {
		viewModelScope.launch {
			_petList.emit(PetRepo.getAllPets())
			this@PetListViewModel.isDark.onEach {
				if (it) {
					applyTheme(DARK_MODE)
				} else {
					applyTheme(LIGHT_MODE)
				}
			}.launchIn(this)
		}
	}

	suspend fun toggleTheme(isLightFlag: Boolean) {
		_isDark.emit(isLightFlag)
	}

}
