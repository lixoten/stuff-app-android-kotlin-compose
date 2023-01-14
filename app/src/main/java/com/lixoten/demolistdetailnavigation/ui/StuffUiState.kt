package com.lixoten.demolistdetailnavigation.ui

import com.lixoten.demolistdetailnavigation.data.DataSource
import com.lixoten.demolistdetailnavigation.model.Stuff

data class StuffUiState(
    val stuffList: List<Stuff> = emptyList(),
    val currentStuff: Stuff = DataSource.defaultStuffItem,
    val isShowingListPage: Boolean = true
)


