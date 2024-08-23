package com.martin.myapplication.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.martin.myapplication.presentation.state.HomeScreenState

@Composable
fun HomeScreen() {

    val homeScreenState = HomeScreenState("Home Screen")


    HomeScreenContent(homeScreenState = homeScreenState)
}


@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
    homeScreenState: HomeScreenState
) {
    Column(
        modifier = modifier
    ) {
        TopWidget()
        BottomWidget(modifier = Modifier, homeScreenState.title)
    }

}

@Composable
fun TopWidget(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
) {
}


@Composable
fun BottomWidget(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
    title: String
) {
    TextButton(
        onClick = { /*TODO*/ },
        modifier = modifier
    ) {
        BasicText(text = title)
    }
}

@Preview
@Composable
fun HomeScreenPreview() {

    HomeScreenContent(homeScreenState = HomeScreenState("Home Screen"))
}