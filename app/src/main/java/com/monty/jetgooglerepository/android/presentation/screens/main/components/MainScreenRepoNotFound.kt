package com.monty.jetgooglerepository.android.presentation.screens.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.monty.jetgooglerepository.android.R
import com.monty.jetgooglerepository.android.presentation.theme.AppTheme
import com.monty.jetgooglerepository.android.presentation.theme.TextColorHint

@Composable
fun MainScreenRepoNotFound(

) {


        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.repo_not_found
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(150.dp),
                )

                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Your search did not match any repositories",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "You could try one of the tips below.",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleSmall,
                        color = TextColorHint
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "-google\n-facebook\n-instagram",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleSmall,
                        color = TextColorHint
                    )
                }
            }
        }

}

@Preview(backgroundColor = 0xFFE4E4E4, showBackground = true)
@Composable
fun MainScreenRepoNotFound_Preview() {
    AppTheme {
        MainScreenRepoNotFound()
    }
}