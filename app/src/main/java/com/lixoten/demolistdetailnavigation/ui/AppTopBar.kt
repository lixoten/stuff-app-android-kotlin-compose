package com.lixoten.demolistdetailnavigation.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.lixoten.demolistdetailnavigation.R


@Composable
fun AppTopBar(
    currentStuffName: String,
    isShowingListPage: Boolean,
    onBackButtonClick: () -> Unit
) {
    TopAppBar(
        title = {
            if (isShowingListPage) {
                Text(text = stringResource(R.string.list_screen_name))
            } else {
                Text(text = currentStuffName)
            }
        },
        navigationIcon =
        if (!isShowingListPage) {
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