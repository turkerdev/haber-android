package dev.turker.haber.article

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
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
        Column {
            Text(text = comments.size.toString()+" comments", style = dev.turker.haber.ui.theme.Typography.labelSmall, modifier = Modifier.padding(0.dp,0.dp,0.dp,2.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(comments){ comment ->
                    CommentCard(comment)
                }
            }
        }
    }
}