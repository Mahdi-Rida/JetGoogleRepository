package com.monty.jetgooglerepository.android.presentation.appcomponents

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.monty.jetgooglerepository.android.presentation.theme.AppTheme
import com.monty.jetgooglerepository.android.presentation.theme.Blue

@Composable
fun AppDialog(
    title: String?,
    text: String,
    positiveButton: String,
    negativeButton: String?,
    onPositiveClick: () -> Unit,
    onNegativeClick: () -> Unit
) {

    AlertDialog(
        onDismissRequest = { onNegativeClick.invoke() },
        title = {
            if (title != null) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        text = {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                lineHeight = 20.sp
            )
        },
        confirmButton = {
            TextButton(
                onClick = onPositiveClick
            ) {
                Text(
                    text = positiveButton,
                    color = Blue,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        dismissButton = {
            if (negativeButton != null) {
                TextButton(
                    onClick = onNegativeClick
                ) {
                    Text(
                        text = negativeButton,
                        color = Blue,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

    )
}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun AppDialog_Preview() {
    AppTheme {
        AppDialog(
            title = "Note",
            text = "Test message 123 123",
            positiveButton = "Yes",
            negativeButton = "No",
            onPositiveClick = {}) {
        }
    }
}