package com.example.androiddevchallenge.ui.theme

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate

object ThemeManager {

	enum class Theme(val str: String) {
		LIGHT_MODE("Light"), DARK_MODE("Dark"), DEFAULT_MODE("Default");
	}

	fun applyTheme(themePref: Theme) {
		when (themePref) {
			Theme.LIGHT_MODE -> {
				AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
			}
			Theme.DARK_MODE -> {
				AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
			}
			else -> {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
					AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
				} else {
					AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
				}
			}
		}
	}
}
