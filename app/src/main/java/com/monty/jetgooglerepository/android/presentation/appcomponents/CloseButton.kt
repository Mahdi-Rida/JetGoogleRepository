package com.monty.jetgooglerepository.android.presentation.appcomponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.monty.jetgooglerepository.android.R
import com.monty.jetgooglerepository.android.presentation.theme.AppTheme

@Composable
fun CloseButton(
    icon: ImageVector,
    modifier:Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(R.string.close),
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
fun CloseIcon_Preview() {
    AppTheme {
        CloseButton(
            icon = Icons.Default.Close,
            onClick = {})
    }
}