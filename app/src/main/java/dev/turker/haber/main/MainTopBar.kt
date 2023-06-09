package dev.turker.haber.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import dev.turker.haber.ui.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(onQueryChange: (String) -> Unit){
    var query by remember { mutableStateOf("") }

    Surface(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp)
            .shadow(elevation = 3.dp, shape = Shapes.medium)
            .fillMaxWidth()){
        TextField(
            value = query,
            onValueChange = {
                query = it
                onQueryChange(it.trim())
            },
            singleLine = true,
            placeholder = { Text(text = "Search News") },
            trailingIcon = {
                IconButton(onClick = { onQueryChange(query.trim()) }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                }
            }
        )
    }
}