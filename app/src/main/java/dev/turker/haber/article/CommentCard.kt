package dev.turker.haber.article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.turker.haber.Comment

@Composable
fun CommentCard(comment: Comment){
    Card(Modifier.fillMaxWidth().padding(4.dp)){
        Text("Guest commenter",
            style = dev.turker.haber.ui.theme.Typography.titleSmall,
            modifier = Modifier.padding(horizontal = 8.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = comment.comment,
            modifier = Modifier.padding(all = 8.dp),
            style = dev.turker.haber.ui.theme.Typography.bodyMedium
        )
    }
}