package com.example.mvi_jetpackcompose.core.presentation.composables.bottomSheet

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryBottomSheet(
    modifier: Modifier = Modifier,
    peekHeight: Dp,
    sheetState: BottomSheetScaffoldState,
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = sheetState,
        sheetContainerColor = Color.Green,
        sheetPeekHeight = peekHeight,
        sheetDragHandle = {

        },
        sheetContent = {
            sheetContent()
        }
    ) {
        content(it)
    }
}