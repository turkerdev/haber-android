package dev.turker.haber.article

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.turker.haber.Comment

@Composable
fun CommentList(comments: Array<Comment>){
    if(comments.isEmpty()){
        Text(textAlign = TextAlign.Center,
            text = "You make the first comment!",
            modifier = Modifier.fillMaxWidth())
    } else {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(comments){ comment ->
                CommentCard(comment)
            }
        }
    }
}