package com.lixoten.demolistdetailnavigation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lixoten.demolistdetailnavigation.model.Stuff

@Composable
fun StuffApp() {
    val viewModel: StuffViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            AppTopBar(
                currentStuffName = uiState.currentStuff.title,
                isShowingListPage = uiState.isShowingListPage,
                onBackButtonClick = { viewModel.navigateToListPage() }
            )
        }
    ) { innerPadding ->
        if (uiState.isShowingListPage) {
            StuffListScreen(
                stuffList = uiState.stuffList,
                onItemClick = {
                    viewModel.updateCurrentStuff(it)
                    viewModel.navigateToDetailPage()
                },
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            StuffDetailScreen(
                stuffItem = uiState.currentStuff,
                onBackPressed = { viewModel.navigateToListPage() },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun StuffListScreen(
    stuffList: List<Stuff>,
    onItemClick: (Stuff) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .padding(8.dp)
    ) {
        // Notes : this is ok, if we needed an ID or Title or...
        // items(stuffList, key = { stuff -> stuff.title}) { stuff ->
        // Notes: this is OK too
        items(stuffList) { stuff ->
            StuffListItem(
                item = stuff,
                onItemClick = onItemClick
            )
        }
        // Notes: Adding Add more 51 items for the hell of it
        items(51) { index ->
            Text(text = "Item: $index")
        }
        // Notes: Add another single item for the hell of it
        item {
            Text(text = "Last item")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StuffListItem(
    item: Stuff,
    onItemClick: (Stuff) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, Color.LightGray),
        elevation = 4.dp,
        onClick = { onItemClick(item) },
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .border(2.dp, Color.LightGray)
                .padding(16.dp)
                .height(intrinsicSize = IntrinsicSize.Max)
        ) {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = "",
                modifier = Modifier.width(100.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(
                    text = item.title,
                )
                Text(
                    text = item.shortDescription,
                )
            }
        }
    }
}

@Composable
fun StuffDetailScreen(
    stuffItem: Stuff,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }
    LazyColumn (modifier = modifier.padding(16.dp)) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = stuffItem.imageResId),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                    )
                    Text(
                        text = stuffItem.title,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.BottomStart)
                    )
                }
            }
        }
        item {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = stuffItem.shortDescription,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = stuffItem.longDescription,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}