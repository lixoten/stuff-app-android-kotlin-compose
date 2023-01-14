package com.lixoten.demolistdetailnavigation.ui

import androidx.lifecycle.ViewModel
import com.lixoten.demolistdetailnavigation.data.DataSource
import com.lixoten.demolistdetailnavigation.model.Stuff
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StuffViewModel: ViewModel() {
    // Notes: We can do it this way
    private val _uiState = MutableStateFlow(
        StuffUiState(
            stuffList = DataSource.stuffList,
            currentStuff = DataSource.stuffList.getOrElse(0) {
                DataSource.defaultStuffItem
            }
        )
    )
    val uiState: StateFlow<StuffUiState> = _uiState.asStateFlow()

    /* Notes: OR We can do it this way
    private val _uiState = MutableStateFlow(StuffUiState())
    val uiState: StateFlow<StuffUiState> = _uiState.asStateFlow()

    init {
        initializeUIState()
    }

    fun initializeUIState() {
        _uiState.value =
            StuffUiState(
                stuffList = DataSource().loadData(),
                currentStuff = DataSource().defaultStuffItem
            )
    }
    */


    fun updateCurrentStuff(stuff: Stuff){
        _uiState.update {currentState ->
            currentState.copy(
                currentStuff = stuff
            )
        }
    }

    fun navigateToListPage() {
        _uiState.update {
            it.copy(isShowingListPage = true)
        }
    }
    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(isShowingListPage = false)
        }
    }
}