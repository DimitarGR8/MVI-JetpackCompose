package com.example.mvi_jetpackcompose.home

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mvi_jetpackcompose.core.presentation.composables.bottomSheet.PrimaryBottomSheet
import com.example.mvi_jetpackcompose.core.presentation.composables.map.PrimaryGoogleMap
import com.example.mvi_jetpackcompose.custom.ext.toRealDp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    HomeScreenContent(viewState = viewState,
        onDrawerButtonClicked = { viewModel.postAction(HomeAction.ChangeDrawerState(true)) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    viewState: HomeState,
    onDrawerButtonClicked: () -> Unit,
) {
    val sheetState = rememberBottomSheetScaffoldState()
    var currentOffset by remember { mutableStateOf(0.dp) }
    var screenSize by remember { mutableStateOf(0.dp) }
    var paddingCalculation by remember { mutableStateOf(0.dp) }

    val boxHeight = 300.dp
    val peekHeight = 100.dp
    val context = LocalContext.current

    PrimaryBottomSheet(
        modifier = Modifier.onSizeChanged {
            screenSize = it.height.dp
        },
        peekHeight = peekHeight,
        sheetState = sheetState,
        sheetContent = {
            Text(
                modifier = Modifier
                    .height(boxHeight)
                    .align(Alignment.CenterHorizontally),
                text = "Bottom sheet: BS = for BE ES meaning B***S***"
            )
        }
    ) {
        currentOffset = sheetState.bottomSheetState.requireOffset().dp
        paddingCalculation = (screenSize - currentOffset - it.calculateBottomPadding()).toRealDp(context)

        PrimaryGoogleMap(
            modifier = Modifier.padding(bottom = if (paddingCalculation > 0.dp) paddingCalculation else 0.dp),
            contentBottomPadding = it.calculateBottomPadding().toRealDp(context)
        ) {

        }

        Button(onClick = { onDrawerButtonClicked() }) {
            Text(text = "Click me")
        }
    }
}