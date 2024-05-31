package com.monty.jetgooglerepository.android.presentation.screens.main.components

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.monty.jetgooglerepository.android.presentation.theme.AppTheme

private enum class Visibility {
    VISIBLE,
    GONE
}

@Composable
fun JumpToTop(
    enabled: Boolean,
    onClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val transition = updateTransition(
        if (enabled) Visibility.VISIBLE else Visibility.GONE,
        label = "1"
    )
    val bottomOffset by transition.animateDp(label = "2") {
        if (it == Visibility.GONE) {
            (-32).dp
        } else {
            32.dp
        }
    }
    if (bottomOffset > 0.dp) {
        ExtendedFloatingActionButton(
            icon = {
                Icon(
                    imageVector = Icons.Filled.ArrowUpward,
                    modifier = Modifier.height(18.dp),
                    contentDescription = null
                )
            },
            text = {
                Text(text = "Jump to Top")
            },
            onClick = onClicked,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White,
            modifier = modifier
                .offset(x = 0.dp, y = -bottomOffset)
                .height(36.dp),
            shape = RoundedCornerShape(16.dp)
        )
    }
}

@Preview
@Composable
fun JumpToTop_Preview() {
    AppTheme {
        JumpToTop(enabled = true, onClicked = {})
    }

}
