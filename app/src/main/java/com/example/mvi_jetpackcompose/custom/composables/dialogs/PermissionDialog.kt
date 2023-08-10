package com.example.mvi_jetpackcompose.custom.composables.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.mvi_jetpackcompose.R
import com.example.mvi_jetpackcompose.custom.permissions.PermissionTextProvider

@Composable
fun PermissionDialog(
    permission: PermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClicked: () -> Unit,
    onGoToAppSettingsClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.default_padding))
                    .clickable {
                        if (isPermanentlyDeclined) onGoToAppSettingsClicked() else onOkClicked()
                    },
                text = if (isPermanentlyDeclined) "Grant permission" else "OK",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        },
        modifier = modifier,
        title = {
            Text(text = "Permission required")
        },
        text = {
            Text(text = permission.getDescription(isPermanentlyDeclined = isPermanentlyDeclined))
        }
    )
}
