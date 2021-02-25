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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.darkBlue
import com.example.androiddevchallenge.ui.theme.lightGray

@Composable
fun PuppiesListScreen(onPuppyListItemClicked: (Puppy) -> Unit) {
    val puppies = arrayListOf(
        Puppy("Haru", 1, Breed.CORGI, Gender.MALE),
        Puppy("Boltie", 2, Breed.PUG, Gender.FEMALE),
        Puppy("Max", 4, Breed.CORGI, Gender.FEMALE),
        Puppy("Bolt", 3, Breed.PUG, Gender.MALE)
    )

    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.background).fillMaxHeight()
    ) {
        Column(
            modifier = Modifier.background(MaterialTheme.colors.background).padding(8.dp)
        ) {
            Spacer(modifier = Modifier.size(20.dp))

            Text(
                text = "Adoppy",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 38.sp,
                color = MaterialTheme.colors.onBackground,
            )

            Text(
                text = "Give a happy place for a puppy to be",
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                color = lightGray
            )

            Spacer(modifier = Modifier.size(20.dp))

            LazyColumn {
                items(puppies) {
                    PuppyListItem(puppy = it, onPuppyListItemClicked)
                    Spacer(modifier = Modifier.size(6.dp))
                }
            }
        }
    }
}

@Composable
private fun PuppyListItem(puppy: Puppy, onPuppyListItemClicked: (Puppy) -> Unit) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(130.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .clickable {
                onPuppyListItemClicked(puppy)
            }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            PuppyListItemImageContainer(puppy.breed)
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = puppy.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = "${puppy.age} years old",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Distance 5k",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = "Distance",
                        tint = Color.Red,
                        modifier = Modifier.sizeIn(maxWidth = 14.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun PuppyListItemImageContainer(puppyBreed: Breed) {
    val imageResourceId =
        if (puppyBreed == Breed.CORGI) R.drawable.ic_corgi_with_bone else R.drawable.ic_pug

    Column(
        modifier = Modifier
            .requiredWidth(140.dp)
            .fillMaxHeight()
            .background(color = darkBlue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = "Puppy Image",
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Preview("List item")
@Composable
private fun ListItemPreview() {
    PuppyListItem(Puppy("Haru", 1, Breed.CORGI, Gender.MALE)) {}
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun PuppiesListScreenLightPreview() {
    MyTheme {
        PuppiesListScreen(onPuppyListItemClicked = { })
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
private fun PuppiesListScreenDarkPreview() {
    MyTheme(darkTheme = true) {
        PuppiesListScreen(onPuppyListItemClicked = { })
    }
}
