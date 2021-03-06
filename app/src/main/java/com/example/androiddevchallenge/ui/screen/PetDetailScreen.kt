package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.components.PetAdoptionDivider
import com.example.androiddevchallenge.ui.components.PetAdoptionSurface
import com.example.androiddevchallenge.ui.data.Pet
import com.example.androiddevchallenge.ui.theme.Neutral8
import com.example.androiddevchallenge.ui.theme.PetAdoptionTheme
import com.example.androiddevchallenge.ui.utils.viewModelProviderFactoryOf
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import kotlin.math.max
import kotlin.math.min

private val TitleHeight = 128.dp
private val GradientScroll = 180.dp
private val ImageOverlap = 115.dp
private val MinTitleOffset = 56.dp
private val MinImageOffset = 12.dp
private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll
private val ExpandedImageSize = 300.dp
private val CollapsedImageSize = 150.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)

@Composable
fun PetDetailScreen(petID: Int, upPress: () -> Unit) {
	val viewModel: PetDetailViewModel = viewModel(
		factory = viewModelProviderFactoryOf { PetDetailViewModel(petID = petID) }
	)
	val pet = viewModel.petDetail.collectAsState()
	Box(Modifier.fillMaxSize()) {
		val scroll = rememberScrollState(0)
		Header(petID)
		Body(scroll)
		Title(pet.value, scroll.value)
		Image(pet.value, scroll.value)
		Up(upPress)
	}
}

@Composable
private fun Header(petID: Int) {
	val gradient = getGradient(petID)
	Spacer(
		modifier = Modifier
			.height(280.dp)
			.fillMaxWidth()
			.background(Brush.horizontalGradient(gradient))
	)
}

@Composable
fun getGradient(petID: Int): List<Color> {
	return when (petID % 2) {
		0 -> PetAdoptionTheme.colors.gradient2_2
		else -> PetAdoptionTheme.colors.gradient3_2
	}
}

@Composable
private fun Up(upPress: () -> Unit) {
	IconButton(
		onClick = upPress,
		modifier = Modifier
			.statusBarsPadding()
			.padding(horizontal = 16.dp, vertical = 10.dp)
			.size(36.dp)
			.background(
				color = Neutral8.copy(alpha = 0.32f),
				shape = CircleShape
			)
	) {
		Icon(
			imageVector = Icons.Outlined.ArrowBack,
			tint = PetAdoptionTheme.colors.iconInteractive,
			contentDescription = stringResource(R.string.label_back)
		)
	}
}

@Composable
private fun Body(scroll: ScrollState) {
	Column {
		Spacer(
			modifier = Modifier
				.fillMaxWidth()
				.statusBarsPadding()
				.height(MinTitleOffset)
		)
		Column(
			modifier = Modifier.verticalScroll(scroll)
		) {
			Spacer(Modifier.height(GradientScroll))
			PetAdoptionSurface(Modifier.fillMaxWidth()) {
				Column {
					Spacer(Modifier.height(ImageOverlap))
					Spacer(Modifier.height(TitleHeight))
					Spacer(Modifier.height(16.dp))
					DetailsItem()
					GenerateItem(stringResource(R.string.habits_header))
					GenerateItem(stringResource(R.string.medication_header))
					GenerateItem(stringResource(R.string.about_header))
					GenerateItem(stringResource(R.string.address_header))
				}
			}
		}
	}
}

@Composable
private fun GenerateItem(header: String) {
	Spacer(Modifier.height(40.dp))
	Text(
		text = header,
		style = MaterialTheme.typography.overline,
		color = PetAdoptionTheme.colors.textHelp,
		modifier = HzPadding
	)
	Spacer(Modifier.height(4.dp))
	Text(
		text = stringResource(R.string.detail_placeholder),
		style = MaterialTheme.typography.body1,
		color = PetAdoptionTheme.colors.textHelp,
		modifier = HzPadding
	)
}

@Composable
private fun DetailsItem() {
	Text(
		text = stringResource(R.string.detail_header),
		style = MaterialTheme.typography.overline,
		color = PetAdoptionTheme.colors.textHelp,
		modifier = HzPadding
	)
	Spacer(Modifier.height(4.dp))
	Text(
		text = stringResource(R.string.detail_placeholder),
		style = MaterialTheme.typography.body1,
		color = PetAdoptionTheme.colors.textHelp,
		modifier = HzPadding
	)
}

@Composable
private fun Title(pet: Pet, scroll: Int) {
	val maxOffset = with(LocalDensity.current) { MaxTitleOffset.toPx() }
	val minOffset = with(LocalDensity.current) { MinTitleOffset.toPx() }
	val offset = (maxOffset - scroll).coerceAtLeast(minOffset)
	Column(
		verticalArrangement = Arrangement.Bottom,
		modifier = Modifier
			.heightIn(min = TitleHeight)
			.statusBarsPadding()
			.graphicsLayer { translationY = offset }
			.background(color = PetAdoptionTheme.colors.uiBackground)
	) {
		Spacer(Modifier.height(16.dp))
		Text(
			text = pet.name,
			style = MaterialTheme.typography.h4,
			color = PetAdoptionTheme.colors.textSecondary,
			modifier = HzPadding
		)
		Text(
			text = pet.category,
			style = MaterialTheme.typography.subtitle1,
			fontSize = 20.sp,
			color = PetAdoptionTheme.colors.textHelp,
			modifier = HzPadding
		)
		Spacer(Modifier.height(4.dp))
		Text(
			text = pet.age,
			style = MaterialTheme.typography.subtitle2,
			color = PetAdoptionTheme.colors.textPrimary,
			modifier = HzPadding
		)
		Spacer(Modifier.height(8.dp))
		PetAdoptionDivider()
	}
}

@Composable
private fun Image(
	pet: Pet,
	scroll: Int
) {
	val collapseRange = with(LocalDensity.current) { (MaxTitleOffset - MinTitleOffset).toPx() }
	val collapseFraction = (scroll / collapseRange).coerceIn(0f, 1f)
	CollapsingImageLayout(
		collapseFraction = collapseFraction,
		modifier = HzPadding.then(Modifier.statusBarsPadding())
	) {
		PetImage(pet, modifier = Modifier.fillMaxSize())
	}
}

@Composable
private fun CollapsingImageLayout(
	collapseFraction: Float,
	modifier: Modifier = Modifier,
	content: @Composable () -> Unit
) {
	Layout(
		modifier = modifier,
		content = content
	) { measurables, constraints ->
		check(measurables.size == 1)
		val imageMaxSize = min(ExpandedImageSize.roundToPx(), constraints.maxWidth)
		val imageMinSize = max(CollapsedImageSize.roundToPx(), constraints.minWidth)
		val imageWidth = lerp(imageMaxSize, imageMinSize, collapseFraction)
		val imagePlaceable = measurables[0].measure(Constraints.fixed(imageWidth, imageWidth))
		val imageY = lerp(MinTitleOffset, MinImageOffset, collapseFraction).roundToPx()
		val imageX = lerp(
			(constraints.maxWidth - imageWidth) / 2,
			constraints.maxWidth - imageWidth,
			collapseFraction
		)
		layout(
			width = constraints.maxWidth,
			height = imageY + imageWidth
		) {
			imagePlaceable.place(imageX, imageY)
		}
	}
}

@Preview("Pet Detail • Light")
@Composable
private fun PetDetailPreview() {
	PetAdoptionTheme(darkTheme = false) {
		PetDetailScreen(
			petID = 1,
			upPress = { }
		)
	}
}

@Preview("Pet Detail • Dark")
@Composable
private fun PetDetailDarkPreview() {
	PetAdoptionTheme(darkTheme = true) {
		PetDetailScreen(
			petID = 1,
			upPress = { }
		)
	}
}
