package com.example.androiddevchallenge.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Female
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Male
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.components.PetAdoptionSurface
import com.example.androiddevchallenge.ui.data.Gender
import com.example.androiddevchallenge.ui.data.Pet
import com.example.androiddevchallenge.ui.data.PetRepo
import com.example.androiddevchallenge.ui.theme.CategoryTextProportion
import com.example.androiddevchallenge.ui.theme.ItemShape
import com.example.androiddevchallenge.ui.theme.MinImageSize
import com.example.androiddevchallenge.ui.theme.PetAdoptionTheme
import com.example.androiddevchallenge.ui.utils.viewModelProviderFactoryOf
import kotlinx.coroutines.launch
import kotlin.math.max

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PetListScreen(selectPet: (Int) -> Unit = {}, darkTheme: Boolean = isSystemInDarkTheme()) {
	val viewModel: PetListViewModel = viewModel(
		factory = viewModelProviderFactoryOf { PetListViewModel(isDark = darkTheme) }
	)
	val pets: List<Pet> by viewModel.petList.collectAsState()
	val isDark: Boolean by viewModel.isDark.collectAsState()
	PetAdoptionSurface(modifier = Modifier.fillMaxSize()) {
		Box(modifier = Modifier.fillMaxSize()) {
			val listState = rememberLazyListState()
			val scope = rememberCoroutineScope()
			Column {
				LocationHeader(isDark, onThemeChange = {
					scope.launch {
						viewModel.toggleTheme(it)
					}
				})
				Divider()
				LazyColumn(
					state = listState,
					modifier = Modifier.fillMaxWidth(),
					contentPadding = PaddingValues(bottom = 80.dp)
				) {
					itemsIndexed(items = pets) { index, puppy ->
						PetListItem(puppy, index, selectPet, getGradient(petID = index))
					}
				}
			}
			val showButton = listState.firstVisibleItemIndex > 0
			AnimatedVisibility(
				visible = showButton,
				enter = fadeIn(),
				exit = fadeOut(),
				modifier = Modifier
					.padding(16.dp)
					.align(Alignment.BottomEnd)
			) {
				ScrollToTopButton {
					scope.launch {
						listState.animateScrollToItem(index = 0)
					}
				}
			}
		}
	}
}

@Composable
fun ScrollToTopButton(onClick: () -> Unit = {}) {
	FloatingActionButton(
		onClick = onClick,
		backgroundColor = PetAdoptionTheme.colors.uiBackground
	) {
		Box {
			IconButton(onClick = onClick) {
				Icon(
					imageVector = Icons.Filled.KeyboardArrowUp,
					contentDescription = stringResource(id = R.string.scroll_to_top),
				)
			}
		}
	}
}

@Composable
private fun LocationHeader(isDark: Boolean, onThemeChange: (Boolean) -> Unit) {
	Row(modifier = Modifier.padding(16.dp)) {
		Row(modifier = Modifier.weight(10f)) {
			Icon(
				imageVector = Icons.Filled.LocationCity,
				contentDescription = stringResource(R.string.location_label)
			)
			Text(
				textAlign = TextAlign.Center,
				text = stringResource(R.string.location_txt),
				style = MaterialTheme.typography.h6,
				color = PetAdoptionTheme.colors.textPrimary
			)
		}
		ThemeToggle(Modifier.weight(1f), isDark, onThemeChange)
	}
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ThemeToggle(
	modifier: Modifier = Modifier, isDark: Boolean,
	onLightChange: (Boolean) -> Unit
) {
	if (isDark) {
		Icon(
			modifier = modifier.clickable { onLightChange(!isDark) },
			imageVector = Icons.Outlined.LightMode,
			tint = PetAdoptionTheme.colors.textPrimary,
			contentDescription = stringResource(R.string.light_mode)
		)
	} else {
		Icon(
			modifier = modifier.clickable { onLightChange(!isDark) },
			imageVector = Icons.Outlined.DarkMode,
			tint = PetAdoptionTheme.colors.textPrimary,
			contentDescription = stringResource(R.string.dark_mode)
		)
	}
}

@Composable
private fun PetListItem(
	pet: Pet,
	index: Int,
	onClick: (Int) -> Unit,
	gradient: List<Color>,
	modifier: Modifier = Modifier
) {
	Layout(
		modifier = modifier
			.padding(16.dp)
			.aspectRatio(1.45f)
			.shadow(elevation = 3.dp, shape = ItemShape)
			.clip(ItemShape)
			.background(Brush.horizontalGradient(gradient))
			.clickable { onClick(index) },
		content = {
			Row {
				Column(
					verticalArrangement = Arrangement.SpaceBetween,
					modifier = Modifier.padding(16.dp)
				) {
					Row {
						Text(
							text = pet.name,
							style = MaterialTheme.typography.h4,
							color = PetAdoptionTheme.colors.textPrimary,
						)
						GenderIcon(pet)
					}
					Text(
						text = pet.category,
						style = MaterialTheme.typography.h6,
						color = PetAdoptionTheme.colors.textPrimary,
					)
					Text(
						text = pet.age,
						style = MaterialTheme.typography.subtitle1,
						color = PetAdoptionTheme.colors.textPrimary,
					)
				}
			}
			PetImage(
				pet = pet,
				modifier = Modifier.fillMaxSize()
			)
		}
	) { measurables, constraints ->
		val textWidth = (constraints.maxWidth * CategoryTextProportion).toInt()
		val textPlaceable = measurables[0].measure(Constraints.fixedWidth(textWidth))
		val imageSize = max(MinImageSize.roundToPx(), constraints.maxHeight)
		val imagePlaceable = measurables[1].measure(Constraints.fixed(imageSize, imageSize))
		layout(
			width = constraints.maxWidth,
			height = constraints.minHeight
		) {
			textPlaceable.place(
				x = 0,
				y = (constraints.maxHeight - textPlaceable.height) / 2
			)
			imagePlaceable.place(
				x = textWidth,
				y = (constraints.maxHeight - imagePlaceable.height) / 2
			)
		}
	}
}

@Composable
private fun GenderIcon(pet: Pet) {
	if (pet.gender == Gender.Male) {
		Icon(
			imageVector = Icons.Outlined.Male,
			tint = PetAdoptionTheme.colors.textPrimary,
			contentDescription = stringResource(R.string.label_male)
		)
	} else {
		Icon(
			imageVector = Icons.Outlined.Female,
			tint = PetAdoptionTheme.colors.textPrimary,
			contentDescription = stringResource(R.string.label_female)
		)
	}
}


@Composable
fun PetImage(pet: Pet, modifier: Modifier = Modifier, elevation: Dp = 0.dp) {
	PetAdoptionSurface(
		color = Color.LightGray,
		elevation = elevation,
		shape = CircleShape,
		modifier = modifier
	) {
		val image: Painter = painterResource(id = pet.image)
		Image(
			painter = image,
			contentDescription = pet.name,
			contentScale = ContentScale.Crop,
			modifier = Modifier.aspectRatio(1f)
		)
	}
}

@Preview("PetList • Light")
@Composable
private fun PetListItemPreview() {
	PetAdoptionTheme(darkTheme = false) {
		val gradient = when (2 % 2) {
			0 -> PetAdoptionTheme.colors.gradient2_2
			else -> PetAdoptionTheme.colors.gradient3_2
		}
		PetListItem(PetRepo.getPet(0), 0, {}, gradient)
	}
}

@Preview("PetList • Dark")
@Composable
private fun PetListItemDarkPreview() {
	PetAdoptionTheme(darkTheme = true) {
		val gradient = when (2 % 2) {
			0 -> PetAdoptionTheme.colors.gradient2_2
			else -> PetAdoptionTheme.colors.gradient3_2
		}
		PetAdoptionTheme {
			PetListItem(PetRepo.getPet(0), 0, {}, gradient)
		}
	}
}

@Preview("Header • Light")
@Composable
private fun LocationHeaderPreview() {
	PetAdoptionTheme(darkTheme = false) {
		LocationHeader(true) {}
	}
}

@Preview("Header • Dark")
@Composable
private fun LocationHeaderDarkPreview() {
	PetAdoptionTheme(darkTheme = true) {
		LocationHeader(false) {}
	}
}
