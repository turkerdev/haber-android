package dev.turker.haber.article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.turker.haber.ApiService
import dev.turker.haber.NewComment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentWrite(articleId:String, refreshComments: ()->Unit){
    var comment by remember { mutableStateOf("") }

    fun post(){
        CoroutineScope(Dispatchers.IO).launch {
            ApiService().article.postComment(NewComment(articleId,comment)).execute()
            comment = ""
            refreshComments()
        }
    }

    Column(Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = comment,
            onValueChange = {comment=it},
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Make a comment")
            },
            trailingIcon = {
            Button(
                onClick = {post()},
                enabled = comment.isNotEmpty(),
                modifier = Modifier.padding(end = 4.dp)) {
                    Text("Send")
            }
        })
    }
}
