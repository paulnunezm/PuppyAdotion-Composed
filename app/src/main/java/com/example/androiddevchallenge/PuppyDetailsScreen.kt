/*
 * Copyright 2021 The Android Open Source Project
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
package com.example.androiddevchallenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.almostWhite
import com.example.androiddevchallenge.ui.theme.darkBlue

@Composable
fun PuppyDetailsScreen(puppy: Puppy, onBackPressed: () -> Unit) {
    val cardCornerRadius = 50.dp
    val bottomRoundedShape = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomEnd = cardCornerRadius,
        bottomStart = cardCornerRadius
    )
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxHeight()
            .background(color = darkBlue)
    ) {

        BackArrowRow {
            onBackPressed()
        }

        Card(
            shape = bottomRoundedShape,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.92f)
        ) {
            Column {

                Picture(puppy.breed)

                Details(puppy = puppy)

                AdoptMeRow {
                    onBackPressed()
                }
            }
        }
    }
}

@Composable
private fun Details(puppy: Puppy) {
    val yearsText = if (puppy.age > 1) "years" else "year"

    Column(modifier = Modifier.padding(16.dp)) {

        Spacer(modifier = Modifier.size(10.dp))

        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = puppy.name,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 40.sp
            )

            Spacer(modifier = Modifier.size(30.dp))

            Text(
                text = puppy.age.toString(),
                fontWeight = FontWeight.Medium,
                fontSize = 36.sp
            )
            Text(
                text = "$yearsText old",
                fontWeight = FontWeight.Light,
                fontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.size(20.dp))

        Row {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "Breed",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
                Text(
                    text = puppy.breed.breedName,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.size(20.dp))

            Column(modifier = Modifier.padding(8.dp)) {

                Text(
                    text = "Gender",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                Text(
                    text = puppy.Gender.gender,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
private fun BackArrowRow(onBackPressed: () -> Unit) {
    Row(
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .clickable {
                onBackPressed()
            }
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack, contentDescription = "go back",
            modifier = Modifier.padding(8.dp), tint = almostWhite
        )
    }
}

@Composable
private fun AdoptMeRow(onBackPressed: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(bottom = 40.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {

        AdoptMeButton {
            onBackPressed()
        }
    }
}

@Composable
private fun AdoptMeButton(onBackPressed: () -> Unit) {
    Button(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth(fraction = 0.9f)
            .height(50.dp),
        onClick = { onBackPressed() },
    ) {
        Text(
            text = "Adopt me now",
            fontWeight = FontWeight.Bold,
            color = almostWhite
        )
    }
}

@Composable
private fun Picture(puppyBreed: Breed) {
    val imageResourceId =
        if (puppyBreed == Breed.CORGI) R.drawable.ic_corgi_with_bone else R.drawable.ic_pug
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.4f)
            .background(color = darkBlue),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = "Puppy Image",
            modifier = Modifier.padding(12.dp)
        )
    }
}

private val testPup = Puppy("Haru", 1, Breed.CORGI, Gender.MALE)

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
private fun Preview() {
    MyTheme {
        PuppyDetailsScreen(testPup) {}
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
private fun PreviewDark() {
    MyTheme(darkTheme = true) {
        PuppyDetailsScreen(testPup) {}
    }
}
