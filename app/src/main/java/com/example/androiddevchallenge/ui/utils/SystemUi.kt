/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.androiddevchallenge.ui.utils

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.luminance

interface SystemUiController {
	fun setStatusBarColor(
	    color: Color,
	    darkIcons: Boolean = color.luminance() > 0.5f,
	    transformColorForLightContent: (Color) -> Color = BlackScrimmed
	)

	fun setNavigationBarColor(
	    color: Color,
	    darkIcons: Boolean = color.luminance() > 0.5f,
	    transformColorForLightContent: (Color) -> Color = BlackScrimmed
	)

	fun setSystemBarsColor(
	    color: Color,
	    darkIcons: Boolean = color.luminance() > 0.5f,
	    transformColorForLightContent: (Color) -> Color = BlackScrimmed
	)
}

val LocalSysUiController = staticCompositionLocalOf<SystemUiController> {
	FakeSystemUiController
}

private val BlackScrim = Color(0f, 0f, 0f, 0.2f) // 20% opaque black
private val BlackScrimmed: (Color) -> Color = { original ->
	BlackScrim.compositeOver(original)
}

private object FakeSystemUiController : SystemUiController {
	override fun setStatusBarColor(
	    color: Color,
	    darkIcons: Boolean,
	    transformColorForLightContent: (Color) -> Color
	) = Unit

	override fun setNavigationBarColor(
	    color: Color,
	    darkIcons: Boolean,
	    transformColorForLightContent: (Color) -> Color
	) = Unit

	override fun setSystemBarsColor(
	    color: Color,
	    darkIcons: Boolean,
	    transformColorForLightContent: (Color) -> Color
	) = Unit
}
