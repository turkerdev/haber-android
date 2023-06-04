package dev.turker.haber.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.turker.haber.Comment
import dev.turker.haber.ui.theme.HaberAppTheme

@Composable
fun CommentCard(comment: Comment){
    Card(Modifier.fillMaxWidth()){
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