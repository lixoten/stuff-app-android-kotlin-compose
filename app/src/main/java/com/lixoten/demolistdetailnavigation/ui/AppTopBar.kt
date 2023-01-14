package com.lixoten.demolistdetailnavigation.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.lixoten.demolistdetailnavigation.R


@Composable
fun AppTopBar(
    currentStuffName: String,
    isShowingListPage: Boolean,
    onBackButtonClick: () -> Unit,
    windowSize: WindowWidthSizeClass
) {
    val isShowingDetailPage = windowSize != WindowWidthSizeClass.Expanded && !isShowingListPage

    TopAppBar(
        title = {
            if (isShowingDetailPage) {
                Text(text = currentStuffName)
            } else {
                Text(text = stringResource(R.string.list_screen_name))
            }
        },
        navigationIcon =
        if (isShowingDetailPage) {
            {
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        } else {
            null
        }
    )
}