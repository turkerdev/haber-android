package dev.turker.haber.article

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import dev.turker.haber.Comment

@Composable
fun CommentList(comments: Array<Comment>){
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(comments){ comment ->
            CommentCard(comment)
        }
    }
}