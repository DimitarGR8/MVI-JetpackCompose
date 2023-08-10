package com.example.mvi_jetpackcompose.custom.composables.row

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.mvi_jetpackcompose.R

@Composable
fun UserRow(
    userIcon: String,
    userName: String,
    modifier: Modifier = Modifier,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = modifier.clickable { onProfileClick() }.height(intrinsicSize = IntrinsicSize.Min).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,

        ) {

        AsyncImage(
            modifier = Modifier.clip(CircleShape).fillMaxHeight(),
            model = userIcon,
            placeholder = painterResource(id = R.drawable.ic_price_tag),
            contentDescription = "Profile Picture"
        )
        Text(modifier = Modifier.fillMaxWidth(),text = userName)
    }
}