package dev.turker.haber.article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.turker.haber.Comment
import dev.turker.haber.ui.theme.Shapes

@Composable
fun CommentCard(comment: Comment){
    Card(modifier= Modifier
        .fillMaxWidth()
        .padding(top = 2.dp, bottom = 2.dp)
        .shadow(elevation = 3.dp, shape = Shapes.medium)){
        Row {
            AsyncImage(
                model = "https://picsum.photos/seed/${comment.id}/64",
                contentDescription = null,
                modifier = Modifier.padding(top = 6.dp, start = 6.dp, end = 2.dp).clip(shape = Shapes.extraLarge)
            )
            Column(modifier = Modifier.padding(12.dp,4.dp)){
                Text("Guest commenter", style = dev.turker.haber.ui.theme.Typography.titleSmall.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ))
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = comment.comment, style = dev.turker.haber.ui.theme.Typography.bodyMedium)
            }
        }
    }
}