package com.example.androiddevchallenge.ui.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.example.androiddevchallenge.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pet(
	val id: Int,
	val name: String,
	val category: String,
	val description: String,
	val age: String,
	@DrawableRes val image: Int,
	val gender: Gender = Gender.Male,
	val animalType: AnimalType = AnimalType.Dog
) : Parcelable

enum class AnimalType {
	Dog, Cat
}

enum class Gender {
	Female, Male
}

object PetRepo {
	fun getPet(petID: Int): Pet = pets.first { it.id == petID }
	fun getAllPets(): List<Pet> = pets
}

private val pets = listOf(
	Pet(
		id = 0,
		name = "Zorro",
		category = "Labrador",
		description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum velit augue, pulvinar nec odio vel, suscipit aliquet odio. Vestibulum finibus tempor elit, id consectetur massa elementum at. In tristique pharetra urna, vel pharetra urna aliquam quis. Etiam sit amet lacus est. Suspendisse ut arcu eget mauris rhoncus scelerisque.",
		image = R.drawable.pet8,
		age = "2 years old"
	),
	Pet(
		id = 1,
		name = "Neo",
		category = "Labrador",
		description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum velit augue, pulvinar nec odio vel, suscipit aliquet odio. Vestibulum finibus tempor elit, id consectetur massa elementum at. In tristique pharetra urna, vel pharetra urna aliquam quis. Etiam sit amet lacus est. Suspendisse ut arcu eget mauris rhoncus scelerisque.",
		image = R.drawable.pet1,
		age = "1 year old"
	),
	Pet(
		id = 2,
		name = "Trinity",
		category = "Golden retriever",
		description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum velit augue, pulvinar nec odio vel, suscipit aliquet odio. Vestibulum finibus tempor elit, id consectetur massa elementum at. In tristique pharetra urna, vel pharetra urna aliquam quis. Etiam sit amet lacus est. Suspendisse ut arcu eget mauris rhoncus scelerisque.",
		image = R.drawable.pet2,
		age = "5 years old",
		gender = Gender.Female
	),
	Pet(
		id = 3,
		name = "Morpheus",
		category = "Pomeranian",
		description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum velit augue, pulvinar nec odio vel, suscipit aliquet odio. Vestibulum finibus tempor elit, id consectetur massa elementum at. In tristique pharetra urna, vel pharetra urna aliquam quis. Etiam sit amet lacus est. Suspendisse ut arcu eget mauris rhoncus scelerisque.",
		image = R.drawable.pet3,
		age = "3 years old"
	),
	Pet(
		id = 4,
		name = "Flash",
		category = "Australian Shepherd",
		description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum velit augue, pulvinar nec odio vel, suscipit aliquet odio. Vestibulum finibus tempor elit, id consectetur massa elementum at. In tristique pharetra urna, vel pharetra urna aliquam quis. Etiam sit amet lacus est. Suspendisse ut arcu eget mauris rhoncus scelerisque.",
		image = R.drawable.pet4,
		age = "4 years old"
	),
	Pet(
		id = 5,
		name = "Bella",
		category = "Cavalier King Charles spaniel",
		description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum velit augue, pulvinar nec odio vel, suscipit aliquet odio. Vestibulum finibus tempor elit, id consectetur massa elementum at. In tristique pharetra urna, vel pharetra urna aliquam quis. Etiam sit amet lacus est. Suspendisse ut arcu eget mauris rhoncus scelerisque.",
		image = R.drawable.pet5,
		age = "2 years old",
		gender = Gender.Female
	),
	Pet(
		id = 6,
		name = "Mars",
		category = "Chihuahua",
		description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum velit augue, pulvinar nec odio vel, suscipit aliquet odio. Vestibulum finibus tempor elit, id consectetur massa elementum at. In tristique pharetra urna, vel pharetra urna aliquam quis. Etiam sit amet lacus est. Suspendisse ut arcu eget mauris rhoncus scelerisque.",
		image = R.drawable.pet6,
		age = "1 year old"
	),
	Pet(
		id = 7,
		name = "Jupiter",
		category = "Husky",
		description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum velit augue, pulvinar nec odio vel, suscipit aliquet odio. Vestibulum finibus tempor elit, id consectetur massa elementum at. In tristique pharetra urna, vel pharetra urna aliquam quis. Etiam sit amet lacus est. Suspendisse ut arcu eget mauris rhoncus scelerisque.",
		image = R.drawable.pet7,
		age = "2 years old"
	),
	Pet(
		id = 8,
		name = "Daisy",
		category = "Greyhound",
		description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum velit augue, pulvinar nec odio vel, suscipit aliquet odio. Vestibulum finibus tempor elit, id consectetur massa elementum at. In tristique pharetra urna, vel pharetra urna aliquam quis. Etiam sit amet lacus est. Suspendisse ut arcu eget mauris rhoncus scelerisque.",
		image = R.drawable.pet8,
		age = "4 years old",
		gender = Gender.Female
	),
	Pet(
		id = 9,
		name = "Daisy",
		category = "Labrador",
		description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum velit augue, pulvinar nec odio vel, suscipit aliquet odio. Vestibulum finibus tempor elit, id consectetur massa elementum at. In tristique pharetra urna, vel pharetra urna aliquam quis. Etiam sit amet lacus est. Suspendisse ut arcu eget mauris rhoncus scelerisque.",
		image = R.drawable.pet9,
		age = "2 years old"
	),
	Pet(
		id = 10,
		name = "Max",
		category = "Greyhound",
		description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum velit augue, pulvinar nec odio vel, suscipit aliquet odio. Vestibulum finibus tempor elit, id consectetur massa elementum at. In tristique pharetra urna, vel pharetra urna aliquam quis. Etiam sit amet lacus est. Suspendisse ut arcu eget mauris rhoncus scelerisque.",
		image = R.drawable.pet10,
		age = "6 years old"
	),
	Pet(
		id = 11,
		name = "Lola",
		category = "Beagle",
		description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum velit augue, pulvinar nec odio vel, suscipit aliquet odio. Vestibulum finibus tempor elit, id consectetur massa elementum at. In tristique pharetra urna, vel pharetra urna aliquam quis. Etiam sit amet lacus est. Suspendisse ut arcu eget mauris rhoncus scelerisque.",
		image = R.drawable.pet11,
		age = "3 years old",
		gender = Gender.Female
	)
)
