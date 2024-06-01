package com.monty.jetgooglerepository.android.presentation.screens.main.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.monty.jetgooglerepository.android.R
import com.monty.jetgooglerepository.android.presentation.theme.AppTheme
import com.monty.jetgooglerepository.android.presentation.theme.TextColorHint

@Composable
fun MainScreenSearchBar(
    modifier: Modifier = Modifier,
    text: String,
    hint:String,
    onTextChange: (String) -> Unit,
    onHasFocus: (Boolean) -> Unit = {}
) {

    val focusRequester = remember {
        FocusRequester()
    }
    val hasFocus = remember {
        mutableStateOf(false)
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier
            .padding(8.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(30.dp)
            )
            .clip(RoundedCornerShape(30.dp))

    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            keyboardActions = KeyboardActions(
                onDone = {
//                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = MaterialTheme.colorScheme.background,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.background,
                errorTextColor = Color.Black
            ),
            maxLines = 1,
            singleLine = true,
            textStyle = MaterialTheme.typography.titleMedium,
            trailingIcon = {
                IconButton(onClick = {
                    onTextChange("")
                }) {
                    Icon(
                        imageVector = if (text.isEmpty()) Icons.Filled.Search else Icons.Filled.Close,
                        contentDescription = stringResource(R.string.contentdescription_searchbaricon),
                        modifier = Modifier
                            .size(20.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .focusRequester(focusRequester)
                .onFocusChanged {
                    onHasFocus(it.isFocused)
                    hasFocus.value = it.isFocused
                }
        )
        if (text.isEmpty()) {
            Text(
                text = hint,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp),
                style = MaterialTheme.typography.titleMedium,
                color = TextColorHint
            )
        }
    }
}

@Preview(
    showBackground = true, backgroundColor = 0xFF282C31,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun MainScreenSearchBar_Preview() {
    AppTheme {
        MainScreenSearchBar(
            text = "",
            hint = "Enter repository name",
            onTextChange = {})
    }
}